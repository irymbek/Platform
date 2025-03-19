package kz.rymbek.platform.common.core.architecture

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kz.rymbek.platform.common.core.architecture.ResultFlow.Loading
import kz.rymbek.platform.common.core.architecture.ResultFlow.Success
import kz.rymbek.platform.common.core.architecture.ResultFlow.Error
import kotlin.toString

sealed interface ResultFlow<out Model> {
    data class Success<out Model>(val data: Model) : ResultFlow<Model>
    data class Error(val exception: Throwable) : ResultFlow<Nothing>
    data object Loading : ResultFlow<Nothing>
    data object Initial : ResultFlow<Nothing>

    suspend fun <Model> Flow<ResultFlow<Model>>.onSuccessFlowEmpty(
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

    suspend fun <Model> Flow<ResultFlow<Model>>.onSuccessFlow(
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

    suspend fun <T, R> Flow<ResultFlow<T>>.mapFlow(
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


    suspend fun onSuccessFlow(
        action: suspend (Model) -> Unit,
    ): ResultFlow<Unit> = when (this) {
        is Success -> {
            action(data)
            Success(Unit)
        }
        is Error -> this
        Loading -> Loading
        Initial -> Initial
    }

    suspend fun <R> map(
        transform: suspend (Model) -> R,
    ): ResultFlow<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
        Loading -> Loading
        Initial -> Initial
    }
}

fun <Model> Flow<Model?>.asResult(): Flow<ResultFlow<Model>> {
    return map<Model?, ResultFlow<Model>> { data ->
        if(data != null ) {
            Success(data)
        } else {
            Loading
        }
    }
        .onStart {
            emit(Loading)
        }
        .catch { exception ->
            Log.e("ERROR_APP", exception.message.toString())
            emit(
                Error(
                    exception
                )
            )
        }
}