package kz.rymbek.platform.common.base.network.base_api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.plugins.resources.put
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.resources.href
import io.ktor.resources.serialization.ResourcesFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import kz.rymbek.platform.common.core.architecture.ResultFlow

open class BaseApi : BaseApiHelper() {
    inline fun <reified T> getUrl(
        resource: T
    ): String {
        return href(ResourcesFormat(), resource)
    }

    inline fun <reified T : Any, reified ResponseT : Any> HttpClient.getDataSafe(
        resource: T,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<ResponseT>> {
        return safeRequestFlow {
            get(
                resource = resource,
                builder = httpRequestBuilder,
            ).body()
        }
    }

    suspend inline fun <reified T : Any, reified ResponseT : Any> HttpClient.getData(
        resource: T,
        httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): ResponseT {
        return get(
            resource = resource,
            builder = httpRequestBuilder,
        ).body()
    }

    inline fun <reified T : Any, reified ResponseT : Any> HttpClient.getListDataSafe(
        resource: T,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<List<ResponseT>>> {
        return safeRequestFlow {
            get(
                resource = resource,
                builder = httpRequestBuilder,
            ).body()
        }
    }

    inline fun <reified T : Any, reified ResponseT : Any> HttpClient.getSetDataSafe(
        resource: T,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<Set<ResponseT>>> {
        return safeRequestFlow {
            get(
                resource = resource,
                builder = httpRequestBuilder,
            ).body()
        }
    }

    inline fun <reified T : Any, reified ResponseT : Any> HttpClient.getMatrixData(
        resource: T,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<List<List<ResponseT>>>> {
        return safeRequestFlow {
            get(
                resource = resource,
                builder = httpRequestBuilder,
            ).body()
        }
    }

    inline fun <reified T : Any, reified ResponseT : Any> HttpClient.postDataSafe(
        resource: T,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<ResponseT>> {
        return safeRequestFlow {
            post(resource = resource) {
                httpRequestBuilder()
                setBody(data)
            }.body()
        }
    }

    suspend inline fun <reified T : Any, reified ResponseT : Any> HttpClient.postData(
        resource: T,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): ResultFlow<ResponseT> {
        return safeRequest {
            post(resource = resource) {
                httpRequestBuilder()
                setBody(data)
            }.body()
        }
    }

    inline fun <reified T : Any, reified ResponseT : Any> HttpClient.putData(
        resource: T,
        data: Any,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<ResponseT>> {
        return safeRequestFlow {
            put(resource = resource) {
                httpRequestBuilder()
                setBody(data)
            }.body()
        }
    }

    suspend inline fun <reified T : Any, reified ResponseT : Any> HttpClient.uploadFile(
        resource: T,
        fileInfo: Pair<String, ByteArray>,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
        key: String,
    ): ResultFlow<ResponseT> {
        val (name, bytes) = fileInfo
        return safeRequest {
            post(resource = resource) {
                httpRequestBuilder()
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append(
                                key = key,
                                value = bytes,
                                headers = Headers.build {
                                    append(
                                        name = HttpHeaders.ContentDisposition,
                                        value = "filename=\"${name}\""
                                    )
                                }
                            )
                        }
                    )
                )
                onUpload { bytesSentTotal, contentLength ->
                    println("Sent $bytesSentTotal bytes from $contentLength")
                }
            }.body()
        }
    }

    suspend inline fun <reified T : Any, reified ResponseT : Any> HttpClient.uploadJsonWithFiles(
        resource: T,
        jsonObject: Any,
        files: List<Triple<String, String, ByteArray>>,
        jsonKey: String = "step",
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): ResultFlow<ResponseT> {
        val jsonString = Json.encodeToString(jsonObject)

        return safeRequest {
            post(resource = resource) {
                httpRequestBuilder()
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            // JSON часть
                            append(
                                key = jsonKey,
                                value = jsonString,
                                headers = Headers.build {
                                    append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                                }
                            )

                            // Файлы
                            files.forEach { (key, filename, bytes) ->
                                append(
                                    key = key,
                                    value = bytes,
                                    headers = Headers.build {
                                        append(
                                            HttpHeaders.ContentDisposition,
                                            "filename=\"$filename\""
                                        )
                                        append(HttpHeaders.ContentType, "image/jpeg")
                                    }
                                )
                            }
                        }
                    )
                )
            }.body()
        }
    }

}