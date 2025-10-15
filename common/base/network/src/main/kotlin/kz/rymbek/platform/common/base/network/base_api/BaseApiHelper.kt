package kz.rymbek.platform.common.base.network.base_api

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kz.rymbek.platform.common.core.architecture.ResultFlow

open class BaseApiHelper {
    private fun String.toThrowable() = Throwable(this)

    suspend fun <T> handleApiCall(
        apiCall: suspend () -> T,
    ): ResultFlow<T> {
        return try {
            val response = apiCall()
            ResultFlow.Success(response)
        } catch (clientRequestException: ClientRequestException) {
            val message = "Status Code: ${clientRequestException.response.status.value} - API Key Missing"
            ResultFlow.Error(message.toThrowable())
        } catch (responseException: ResponseException) {
            val message = "Status Code: ${responseException.response.status.value}"
            ResultFlow.Error(message.toThrowable())
        } catch (serializationException: SerializationException) {
            val message = "Serialization error: ${serializationException.message}"
            ResultFlow.Error(message.toThrowable())
        } catch (exception: Exception) {
            val message = "Unknown error: ${exception.message}"
            ResultFlow.Error(message.toThrowable())
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