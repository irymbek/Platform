package kz.rymbek.platform.common.core.architecture

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Error(val exception: Throwable) : NetworkResult<Nothing>
    data object Loading : NetworkResult<Nothing>
}

/* ---- NetworkResult Quick Checks ---- */
val NetworkResult<*>.isLoading get() = this is NetworkResult.Loading
val NetworkResult<*>.isSuccess get() = this is NetworkResult.Success
val NetworkResult<*>.isError get() = this is NetworkResult.Error

/* ---- NetworkResult Operators ---- */

/**
 * Преобразует Success данные. Полезно для маппинга Remote -> Domain моделей.
 */
inline fun <T, R> NetworkResult<T>.mapResult(transform: (T) -> R): NetworkResult<R> = when (this) {
    is NetworkResult.Success -> NetworkResult.Success(transform(data))
    is NetworkResult.Error -> NetworkResult.Error(exception)
    NetworkResult.Loading -> NetworkResult.Loading
}

/**
 * Трансформирует данные внутри NetworkResult.Success через suspend-лямбду.
 * Loading и Error пробрасываются без изменений.
 */
fun <T, R> Flow<NetworkResult<T>>.mapFlow(
    transform: suspend (T) -> R
): Flow<ResultFlow<R>> = map { result ->
    when (result) {
        is NetworkResult.Success -> NetworkResult.Success(transform(result.data))
        is NetworkResult.Error -> NetworkResult.Error(result.exception)
        NetworkResult.Loading -> NetworkResult.Loading
    }.toResultFlow()
}

/**
 * Выполняет действие при успехе (например, запись в БД),
 * не меняя при этом тип и данные потока.
 */
fun <T> Flow<NetworkResult<T>>.onSuccess(
    action: suspend (T) -> Unit
): Flow<NetworkResult<T>> = map { result ->
    if (result is NetworkResult.Success) {
        action(result.data)
    }
    result
}

/**
 * Выполняет действие и возвращает Unit при успехе.
 * Исправленная версия вашего метода (убрана зависимость от ResultFlow.Error).
 */
fun <Model> Flow<NetworkResult<Model>>.onSuccessUnit(
    action: suspend (Model) -> Unit
): Flow<ResultFlow<Unit>> = map { result ->
    when (result) {
        is NetworkResult.Success -> {
            action(result.data)
            NetworkResult.Success(Unit)
        }
        is NetworkResult.Error -> NetworkResult.Error(result.exception)
        NetworkResult.Loading -> NetworkResult.Loading
    }.toResultFlow()
}

fun <T> NetworkResult<T>.toDataResult(): DataResult<T> = when (this) {
    is NetworkResult.Success -> DataResult.Success(data)
    is NetworkResult.Error -> DataResult.Error(exception)
    NetworkResult.Loading -> DataResult.Loading()
}

/**
 * Расширение для Flow, чтобы делать конвертацию на лету
 */
fun <T> Flow<NetworkResult<T>>.mapToDataResult(): Flow<DataResult<T>> =
    this.map { it.toDataResult() }


/**
 * Конвертирует технический NetworkResult в старый ResultFlow.
 * Полезно для миграции, чтобы использовать onSuccessUnit и mapFlow.
 */
fun <T> NetworkResult<T>.toResultFlow(): ResultFlow<T> = when (this) {
    is NetworkResult.Success -> ResultFlow.Success(data)
    is NetworkResult.Error -> ResultFlow.Error(exception)
    NetworkResult.Loading -> ResultFlow.Loading
}

/**
 * Оператор для Flow
 */
fun <T> Flow<NetworkResult<T>>.toResultFlow(): Flow<ResultFlow<T>> =
    this.map { it.toResultFlow() }