package kz.rymbek.platform.common.base.network.base_api

import android.util.Log
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kz.rymbek.platform.common.core.architecture.ResultFlow

open class BaseApiHelper {
    suspend fun <T> handleApiCall(
        apiCall: suspend () -> T,
    ): ResultFlow<T> {
        return try {
            val response = apiCall()
            ResultFlow.Success(response)
        } catch (clientRequestException: ClientRequestException) {
            val message = "Status Code: ${clientRequestException.response.status.value} - API Key Missing"
            Log.d("BaseApiHelper", message, clientRequestException)
            ResultFlow.Error(clientRequestException)
        } catch (responseException: ResponseException) {
            val message = "Status Code: ${responseException.response.status.value} - HttpExceptions"
            Log.d("BaseApiHelper", message, responseException)
            ResultFlow.Error(responseException)
        } catch (serializationException: SerializationException) {
            val message = "Serialization error: ${serializationException.message}"
            Log.d("BaseApiHelper", message, serializationException)
            ResultFlow.Error(serializationException)
        } catch (exception: Exception) {
            val message = "Unknown error: ${exception.message}"
            Log.d("BaseApiHelper", message, exception)
            ResultFlow.Error(exception)
        }
    }

    inline fun <reified T> safeRequestFlow(
        noinline apiCall: suspend () -> T
    ): Flow<ResultFlow<T>> = flow {
        emit(ResultFlow.Loading)
        emit(handleApiCall(apiCall))
    }

    suspend inline fun <reified T> safeRequest(
        noinline apiCall: suspend () -> T,
    ): ResultFlow<T> {
        return handleApiCall(apiCall)
    }
}