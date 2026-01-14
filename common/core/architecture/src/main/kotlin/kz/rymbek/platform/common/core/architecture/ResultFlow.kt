package kz.rymbek.platform.common.core.architecture

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kz.rymbek.platform.common.core.architecture.ResultFlow.Empty
import kz.rymbek.platform.common.core.architecture.ResultFlow.Error
import kz.rymbek.platform.common.core.architecture.ResultFlow.Initial
import kz.rymbek.platform.common.core.architecture.ResultFlow.Loading
import kz.rymbek.platform.common.core.architecture.ResultFlow.Success

sealed interface ResultFlow<out Model> {
    data class Success<out Model>(val data: Model) : ResultFlow<Model>
    data class Error(val exception: Throwable) : ResultFlow<Nothing>
    data object Loading : ResultFlow<Nothing>
    data object Initial : ResultFlow<Nothing>
    data object Empty : ResultFlow<Nothing>
}

/* ---- Quick checks ---- */
val ResultFlow<*>.isLoading get() = this is Loading
val ResultFlow<*>.isSuccess get() = this is Success
val ResultFlow<*>.isError get() = this is Error
val ResultFlow<*>.isInitial get() = this is Initial
val ResultFlow<*>.isEmpty get() = this is Empty

/* ---- Data accessors ---- */
fun <T> ResultFlow<T>.getOrNull(): T? = when (this) {
    is Success -> data
    else -> null
}

fun <T> ResultFlow<T>.getOrThrow(): T = when (this) {
    is Success -> data
    is Error -> throw exception
    else -> throw IllegalStateException("Result is not Success: $this")
}

fun <T> ResultFlow<T>.getOrElse(defaultValue: T): T = when (this) {
    is Success -> data
    else -> defaultValue
}

/* ---- Status combinators ---- */

/**
 * Комбинирует список состояний в единый статус:
 * Priority: Error > Loading > Empty > all Success -> Success(Unit) > Initial
 */
fun List<ResultFlow<*>>.combineStatus(): ResultFlow<Unit> {
    return when {
        any { it is Error } -> first { it is Error } as Error
        any { it is Loading } -> Loading
        any { it is Empty } -> Empty
        isNotEmpty() && all { it is Success } -> Success(Unit)
        else -> Initial
    }
}

fun combineStatus(vararg results: ResultFlow<*>): ResultFlow<Unit> =
    results.toList().combineStatus()

/**
 * Combine multiple Flow<ResultFlow<*>> into a single Flow<ResultFlow<Unit>> (status).
 * Если нет потоков — вернёт Flow с Initial.
 */
fun combineStatusFlows(vararg flows: Flow<ResultFlow<*>>): Flow<ResultFlow<Unit>> {
    if (flows.isEmpty()) return flowOf(Initial)
    return combine(*flows) { arr -> arr.toList().combineStatus() }
}

/* ---- Data combinators ---- */

/**
 * Комбинирует список ResultFlow<T> в ResultFlow<List<T>>.
 * Priority: Error > Loading > Empty (если есть хоть один Empty) > все Success -> Success(list) > Initial
 */
fun <T> List<ResultFlow<T>>.combineData(): ResultFlow<List<T>> =
    when {
        any { it is Error } -> first { it is Error } as Error
        any { it is Loading } -> Loading
        any { it is Empty } -> Empty
        isNotEmpty() && all { it is Success } -> Success(map { (it as Success).data })
        else -> Initial
    }

/**
 * Combine multiple Flow<ResultFlow<T>> (один и тот же T) into Flow<ResultFlow<List<T>>>.
 * Если нет потоков — вернёт Flow с Initial.
 */
fun <T> combineDataFlows(vararg flows: Flow<ResultFlow<T>>): Flow<ResultFlow<List<T>>> {
    if (flows.isEmpty()) return flowOf(Initial)
    return combine(*flows) { arr ->
        // arr: Array<ResultFlow<T>> (star-projected at runtime)
        @Suppress("UNCHECKED_CAST")
        (arr.toList() as List<ResultFlow<T>>).combineData()
    }
}

/* ---- ResultFlow helpers (to reduce duplication) ---- */

/**
 * Синхронный map для ResultFlow (не suspend).
 */
inline fun <T, R> ResultFlow<T>.mapResult(transform: (T) -> R): ResultFlow<R> = when (this) {
    is Success -> Success(transform(data))
    is Error -> this
    Loading -> Loading
    Initial -> Initial
    Empty -> Empty
}

/* ---- Flow operators ---- */

/**
 * Выполнить action при каждом Success (возврат неизменённого ResultFlow).
 */
fun <Model> Flow<ResultFlow<Model>>.onSuccess(
    action: suspend (Model) -> Unit
): Flow<ResultFlow<Model>> = map { result ->
    if (result is Success) {
        action(result.data)
    }
    result
}

/**
 * Как mapFlow, но action возвращает Success(Unit) при успешном результате.
 * Empty/Initial/Loading/Error передаются дальше без изменений.
 */
fun <Model> Flow<ResultFlow<Model>>.onSuccessUnit(
    action: suspend (Model) -> Unit
): Flow<ResultFlow<Unit>> = map { result ->
    when (result) {
        is Success -> {
            action(result.data)
            Success(Unit)
        }

        is Error -> result
        Loading -> Loading
        Initial -> Initial
        Empty -> Empty
    }
}

/**
 * Трансформирует данные внутри Success через suspend-лямбду.
 * Остальные состояния пробрасываются без изменений.
 */
fun <T, R> Flow<ResultFlow<T>>.mapFlow(
    transform: suspend (T) -> R
): Flow<ResultFlow<R>> = map { result ->
    when (result) {
        is Success -> Success(transform(result.data))
        is Error -> result
        Loading -> Loading
        Initial -> Initial
        Empty -> Empty
    }
}

inline fun <T> ResultFlow<T>.onSuccess(block: (T) -> Unit): ResultFlow<T> {
    if (this is Success) block(data)
    return this
}

inline fun <T> ResultFlow<T>.onError(block: (Throwable) -> Unit): ResultFlow<T> {
    if (this is Error) block(exception)
    return this
}

inline fun <T> ResultFlow<T>.onLoading(block: () -> Unit): ResultFlow<T> {
    if (this is Loading) block()
    return this
}

inline fun <T> ResultFlow<T>.onMessage(
    successMessage: String? = null,
    onMessage: (String) -> Unit
): ResultFlow<T> {
    when (this) {
        is Success -> {
            if (successMessage != null) {
                onMessage(successMessage)
            }
            else if (data is String && data.isNotEmpty()) {
                onMessage(data)
            }
        }
        is Error -> onMessage(exception.message ?: "Ошибка")
        else -> {}
    }
    return this
}

/* ---- Convenience: map Flow<T?> -> ResultFlow<T> ---- */
/**
 * Преобразует Flow<T?> в Flow<ResultFlow<T>>:
 * - onStart -> emit(Loading)
 * - null -> Empty (если emptyOnNull = true) или Initial (если false)
 * - non-null -> Success
 * - catch -> Error
 */
fun <Model> Flow<Model?>.asResult(
    emptyOnNull: Boolean = true
): Flow<ResultFlow<Model>> {
    return this
        .map<Model?, ResultFlow<Model>> { data ->
            if (data == null) {
                if (emptyOnNull) Empty else Initial
            } else {
                Success(data)
            }
        }
        .onStart { emit(Loading) }
        .catch { throwable ->
            Log.e("ResultFlow", throwable.message.orEmpty())
            emit(Error(throwable))
        }
}