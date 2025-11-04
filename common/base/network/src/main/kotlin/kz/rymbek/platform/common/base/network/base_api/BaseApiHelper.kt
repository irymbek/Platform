package kz.rymbek.platform.common.base.network.base_api

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kotlin.coroutines.cancellation.CancellationException

open class BaseApiHelper {
    protected open fun log(msg: String) = println("[BaseApi] $msg")

    /**
     * Generic error handling. Returns a ResultFlow<T> with a readable message.
     */
    suspend fun <T> safeCall(block: suspend () -> T): ResultFlow<T> {
        return try {
            ResultFlow.Success(block())
        } catch (e: CancellationException) {
            throw e // не мешаем отмене корутины
        } catch (e: ClientRequestException) {
            val status = e.response.status.value
            val body = runCatching { e.response.bodyAsText() }.getOrNull()
            val msg = buildString {
                append("Ошибка $status")
                body?.takeIf { it.isNotBlank() }?.let { append(": ${it.trim().take(200)}") }
            }
            log(msg)
            ResultFlow.Error(Throwable(msg))
        } catch (e: ResponseException) {
            val status = e.response.status.value
            val body = runCatching { e.response.bodyAsText() }.getOrNull()
            val msg = buildString {
                append("Сервер вернул $status")
                body?.takeIf { it.isNotBlank() }?.let { append(": ${it.trim().take(200)}") }
            }
            log(msg)
            ResultFlow.Error(Throwable(msg))
        } catch (e: SerializationException) {
            val msg = "Ошибка парсинга: ${e.message ?: e::class.simpleName}"
            log(msg)
            ResultFlow.Error(Throwable(msg))
        } catch (e: Exception) {
            val msg = e.message ?: e::class.simpleName ?: "Неизвестная ошибка"
            log("Unknown: $msg")
            ResultFlow.Error(Throwable(msg))
        }
    }

    inline fun <reified T> requestFlowSafe(
        noinline apiCall: suspend () -> T
    ): Flow<ResultFlow<T>> = flow {
        emit(ResultFlow.Loading)
        emit(safeCall(apiCall))
    }

    suspend inline fun <reified T> requestSafe(
        noinline apiCall: suspend () -> T,
    ): ResultFlow<T> = safeCall(apiCall)
}
