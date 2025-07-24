package kz.rymbek.platform.common.core.architecture

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kz.rymbek.platform.common.core.architecture.ResultFlow.Error
import kz.rymbek.platform.common.core.architecture.ResultFlow.Initial
import kz.rymbek.platform.common.core.architecture.ResultFlow.Loading
import kz.rymbek.platform.common.core.architecture.ResultFlow.Success

sealed interface ResultFlow<out Model> {
    data class Success<out Model>(val data: Model) : ResultFlow<Model>
    data class Error(val exception: Throwable) : ResultFlow<Nothing>
    data object Loading : ResultFlow<Nothing>
    data object Initial : ResultFlow<Nothing>
}

fun <Model> Flow<ResultFlow<Model>>.onSuccess(
    action: suspend (Model) -> Unit
): Flow<ResultFlow<Model>> {
    return this.map { result ->
        when (result) {
            is Success -> {
                action(result.data)
                result
            }
            is Error -> result
            Loading -> Loading
            Initial -> Initial
        }
    }
}

fun <T, R> Flow<ResultFlow<T>>.mapFlow(
    transform: suspend (T) -> R
): Flow<ResultFlow<R>> {
    return this.map { result ->
        when (result) {
            is Success -> Success(transform(result.data))
            is Error -> result
            Loading -> Loading
            Initial -> Initial
        }
    }
}

fun <Model> Flow<ResultFlow<Model>>.onSuccessUnit(
    action: suspend (Model) -> Unit
): Flow<ResultFlow<Unit>> {
    return this.map { result ->
        when (result) {
            is Success -> {
                action(result.data)
                Success(Unit)
            }
            is Error -> result
            Loading -> Loading
            Initial -> Initial
        }
    }
}

fun <Model> Flow<Model?>.asResult(): Flow<ResultFlow<Model>> {
    return this.map<Model?, ResultFlow<Model>> { data ->
            if (data != null){
                Success(data)
            } else {
                Initial
            }
        }
        .onStart { emit(Loading) }
        .catch { throwable ->
            Log.e("ERROR_APP", throwable.message.toString())
            emit(Error(throwable))
        }
}
