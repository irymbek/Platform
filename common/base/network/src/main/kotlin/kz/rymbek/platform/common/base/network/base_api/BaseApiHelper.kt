package kz.rymbek.platform.common.base.network.base_api

import android.util.Log
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kotlin.coroutines.cancellation.CancellationException

open class BaseApiHelper {
    protected open fun log(msg: String) = println("[BaseApi] $msg")

    suspend fun <T> safeCall(block: suspend () -> T): ResultFlow<T> {
        return try {
            ResultFlow.Success(block())
        } catch (e: CancellationException) {
            throw e
        } catch (e: ResponseException) {
            val errorMsg = parseError(e.response)
            log("API Error: ${e.response.status} -> $errorMsg")
            ResultFlow.Error(Throwable(errorMsg))
        } catch (e: SerializationException) {
            log("Serialization Error: ${e.message}")
            ResultFlow.Error(Throwable("Ошибка обработки данных"))
        } catch (_: IOException) {
            ResultFlow.Error(Throwable("Нет подключения к интернету"))
        } catch (e: Exception) {
            log("Unknown Error: ${e.message}")
            ResultFlow.Error(Throwable("Неизвестная ошибка"))
        }
    }

    private suspend fun parseError(response: HttpResponse): String {
        val body = response.bodyAsText()
        Log.d("BaseApiHelper", "parseError: $body")
        return runCatching {
            val model = json.decodeFromString<ApiErrorModel>(body)
            model.toUserMessage()
        }.getOrElse {
            getDefaultMessage(response.status)
        }
    }

    private fun getDefaultMessage(status: HttpStatusCode): String {
        return when (status.value) {
            400 -> "Неверные данные запроса"
            401 -> "Требуется авторизация"
            403 -> "Доступ запрещен"
            404 -> "Ресурс не найден"
            429 -> "Слишком много запросов"
            in 500..599 -> "Сервер временно недоступен. Попробуйте позже."
            else -> "Ошибка сервера (Код: ${status.value})"
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
