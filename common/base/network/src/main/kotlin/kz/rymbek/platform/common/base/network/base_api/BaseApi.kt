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
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.core.architecture.ResultFlow

open class BaseApi : BaseApiHelper() {
    inline fun <reified Resource : Any, reified Response : Any> HttpClient.getDataSafe(
        resource: Resource,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Flow<ResultFlow<Response>> = requestFlowSafe {
        get(resource = resource, builder = httpRequestBuilder).body()
    }

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.getData(
        resource: Resource,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Response = get(resource = resource, builder = httpRequestBuilder).body()

    inline fun <reified Resource : Any, reified Response : Any> HttpClient.postDataSafe(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<Response>> = requestFlowSafe {
        post(resource = resource) {
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.postData(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): ResultFlow<Response> = requestSafe {
        post(resource = resource) {
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    inline fun <reified Resource : Any, reified Response : Any> HttpClient.putDataSafe(
        resource: Resource,
        data: Any,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Flow<ResultFlow<Response>> = requestFlowSafe {
        put(resource = resource){
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.putData(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): ResultFlow<Response> = requestSafe {
        put(resource = resource) {
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.uploadFile(
        resource: Resource,
        fileName: String,
        bytes: ByteArray,
        key: String,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
        noinline progressCallback: ((bytesSent: Long, contentLength: Long?) -> Unit)? = null
    ): ResultFlow<Response> = requestSafe {
        post(resource = resource) {
            httpRequestBuilder()
            setBody(
                MultiPartFormDataContent(formData {
                    append(
                        key = key,
                        value = bytes,
                        headers = Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"$fileName\"")
                        }
                    )
                })
            )
            if (progressCallback != null) {
                onUpload { bytesSentTotal, contentLength ->
                    progressCallback(bytesSentTotal, contentLength)
                }
            }
        }.body()
    }
}