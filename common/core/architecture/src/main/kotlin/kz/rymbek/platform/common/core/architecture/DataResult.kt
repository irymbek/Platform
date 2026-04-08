package kz.rymbek.platform.common.core.architecture

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

sealed interface DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>
    data class Error<out T>(
        val exception: Throwable,
        val data: T? = null  // кэш остаётся при ошибке
    ) : DataResult<T>
    data class Loading<out T>(
        val data: T? = null  // кэш виден пока грузится
    ) : DataResult<T>
    data object Initial : DataResult<Nothing>
    data object Empty : DataResult<Nothing>
}

/* ---- DataResult Quick Checks ---- */
val DataResult<*>.isLoading get() = this is DataResult.Loading
val DataResult<*>.isSuccess get() = this is DataResult.Success
val DataResult<*>.isError get() = this is DataResult.Error
val DataResult<*>.isEmpty get() = this is DataResult.Empty

/**
 * Самый важный аксессуар. Пытается достать данные из ЛЮБОГО состояния.
 */
val <T> DataResult<T>.dataOrNull: T? get() = when (this) {
    is DataResult.Success -> data
    is DataResult.Error -> data
    is DataResult.Loading -> data
    else -> null
}

fun <T> DataResult<T>.getOrThrow(): T = when (this) {
    is DataResult.Success -> data
    is DataResult.Error -> throw exception
    else -> throw IllegalStateException("No data in $this")
}

/* ---- DataResult Map ---- */
inline fun <T, R> DataResult<T>.mapResult(transform: (T) -> R): DataResult<R> = when (this) {
    is DataResult.Success -> DataResult.Success(transform(data))
    is DataResult.Loading -> DataResult.Loading(data?.let(transform))
    is DataResult.Error -> DataResult.Error(exception, data?.let(transform))
    DataResult.Empty -> DataResult.Empty
    DataResult.Initial -> DataResult.Initial
}

/**
 * Выполняет действие при каждом Success событии в потоке.
 */
fun <Model> Flow<DataResult<Model>>.onSuccess(
    action: suspend (Model) -> Unit
): Flow<DataResult<Model>> = onEach { result ->
    if (result is DataResult.Success) {
        action(result.data)
    }
}

/**
 * Выполняет действие только если результат Success.
 * Возвращает сам DataResult для цепочки вызовов.
 */
inline fun <T> DataResult<T>.onSuccess(block: (T) -> Unit): DataResult<T> {
    if (this is DataResult.Success) block(data)
    return this
}

/**
 * Выполняет действие только если результат Error.
 */
inline fun <T> DataResult<T>.onError(block: (Throwable) -> Unit): DataResult<T> {
    if (this is DataResult.Error) block(exception)
    return this
}

/**
 * Трансформирует данные во всех состояниях (Success, Loading, Error), где они есть.
 */
fun <T, R> Flow<DataResult<T>>.mapFlow(
    transform: suspend (T) -> R
): Flow<DataResult<R>> = map { it.mapResult { data -> transform(data) } }

/**
 * Преобразует Flow из БД (например, Room) в поток состояний DataResult.
 */
fun <Model> Flow<Model?>.asDataResult(
    emptyOnNull: Boolean = true
): Flow<DataResult<Model>> = this
    .map<Model?, DataResult<Model>> { data ->
        if (data == null) {
            if (emptyOnNull) DataResult.Empty else DataResult.Initial
        } else {
            DataResult.Success(data)
        }
    }
    .onStart { emit(DataResult.Loading()) }
    .catch { emit(DataResult.Error(it)) }

/**
 * Приоритет: Error > Loading > Success.
 * Данные комбинируются в список, если они есть.
 */
fun <T> List<DataResult<T>>.combineData(): DataResult<List<T>> {
    val anyError = filterIsInstance<DataResult.Error<T>>().firstOrNull()
    val anyLoading = any { it is DataResult.Loading }
    val allData = mapNotNull { it.dataOrNull }

    return when {
        anyError != null -> DataResult.Error(anyError.exception, allData.takeIf { it.size == size })
        anyLoading -> DataResult.Loading(allData.takeIf { it.size == size })
        all { it is DataResult.Success } -> DataResult.Success(allData)
        any { it is DataResult.Empty } -> DataResult.Empty
        else -> DataResult.Initial
    }
}