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
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.resources.href
import io.ktor.resources.serialization.ResourcesFormat
import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.core.architecture.ResultFlow

open class BaseApi : BaseApiHelper() {
    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.getUnsafe(
        resource: Resource,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Response = get(resource = resource, builder = httpRequestBuilder).body()

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.getSafe(
        resource: Resource,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): ResultFlow<Response> = requestSafe {
        get(resource = resource, builder = httpRequestBuilder).body()
    }

    inline fun <reified Resource : Any, reified Response : Any> HttpClient.getSafeFlow(
        resource: Resource,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Flow<ResultFlow<Response>> = requestFlowSafe {
        get(resource = resource, builder = httpRequestBuilder).body()
    }

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.postUnsafe(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Response = post(resource = resource) {
        httpRequestBuilder()
        setBody(data)
    }.body()

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.postSafe(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): ResultFlow<Response> = requestSafe {
        post(resource = resource) {
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    inline fun <reified Resource : Any, reified Response : Any> HttpClient.postSafeFlow(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): Flow<ResultFlow<Response>> = requestFlowSafe {
        post(resource = resource) {
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.putUnsafe(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Response = put(resource = resource) {
        httpRequestBuilder()
        setBody(data)
    }.body()

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.putSafe(
        resource: Resource,
        data: Any? = null,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): ResultFlow<Response> = requestSafe {
        put(resource = resource) {
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    inline fun <reified Resource : Any, reified Response : Any> HttpClient.putSafeFlow(
        resource: Resource,
        data: Any,
        crossinline httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): Flow<ResultFlow<Response>> = requestFlowSafe {
        put(resource = resource) {
            httpRequestBuilder()
            setBody(data)
        }.body()
    }

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.submitFormUnsafe(
        resource: Resource,
        formParameters: Parameters
    ): Response = submitForm(url = href(ResourcesFormat(), resource), formParameters = formParameters).body()

    suspend inline fun <reified Resource : Any, reified Response : Any> HttpClient.submitFormSafe(
        resource: Resource,
        formParameters: Parameters
    ): ResultFlow<Response> = requestSafe {
        val url = href(ResourcesFormat(), resource)
        submitForm(url = url, formParameters = formParameters).body()
    }

    inline fun <reified Resource : Any, reified Response : Any> HttpClient.submitFormSafeFlow(
        resource: Resource,
        formParameters: Parameters
    ): Flow<ResultFlow<Response>> = requestFlowSafe {
        val url = href(ResourcesFormat(), resource)
        submitForm(url = url, formParameters = formParameters).body()
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