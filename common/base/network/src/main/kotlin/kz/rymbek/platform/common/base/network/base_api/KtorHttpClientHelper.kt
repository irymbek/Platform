package kz.rymbek.platform.common.base.network.base_api

import android.util.Log
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    allowSpecialFloatingPointValues = true
    explicitNulls = false
    coerceInputValues = true
    prettyPrint = true
}

object KtorHttpClientHelper {
    fun HttpClientConfig<*>.installHttpTimeout(
        timeoutDuration: Long = 50_000L,
    ) {
        install(HttpTimeout) {
            requestTimeoutMillis = timeoutDuration
            connectTimeoutMillis = timeoutDuration
            socketTimeoutMillis = timeoutDuration
        }
    }

    fun HttpClientConfig<*>.installHttpRequestRetry() {
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
    }

    fun HttpClientConfig<*>.installResources() {
        install(Resources)
    }

    fun HttpClientConfig<*>.installContentNegotiation() {
        install(ContentNegotiation) {
            json(
                json = json,
                contentType = ContentType.Application.Json,
            )
        }
    }

    fun HttpClientConfig<*>.installLogging() {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("HTTP =>", message)
                }
            }
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }

    fun HttpClientConfig<*>.installDefaultRequest(
        hostNetwork: String,
        urlProtocol: URLProtocol = URLProtocol.HTTPS,
        block: URLBuilder.() -> Unit = {}
    ) {
        install(DefaultRequest) {
            host = hostNetwork
            url {
                protocol = urlProtocol
                block()
            }
            contentType(ContentType.Application.Json)
        }
    }
}