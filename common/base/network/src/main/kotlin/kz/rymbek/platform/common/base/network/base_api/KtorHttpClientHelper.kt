package kz.rymbek.platform.common.base.network.base_api

import android.util.Log
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.io.File

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
        timeoutDuration: Long = 20_000L,
    ) {
        install(HttpTimeout) {
            requestTimeoutMillis = timeoutDuration
            connectTimeoutMillis = timeoutDuration
            socketTimeoutMillis = timeoutDuration
        }
    }

    fun HttpClientConfig<*>.installHttpRequestRetry() {
        install(HttpRequestRetry) {
            maxRetries = 3
            retryOnException(retryOnTimeout = true)
            retryIf { _, response ->
                response.status.value in 500..599
            }
            exponentialDelay()
        }
    }

    fun HttpClientConfig<*>.installResources() {
        install(Resources)
    }

    fun HttpClientConfig<*>.installCaching() {
        install(HttpCache) {
            val cacheFile = File("app_cache")
            publicStorage(FileStorage(cacheFile))
        }
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
    ) {
        install(DefaultRequest) {
            host = hostNetwork
            url {
                protocol = urlProtocol
            }
            contentType(ContentType.Application.Json)
        }
    }


    fun HttpClientConfig<*>.installAuth(
        tokenProvider: suspend () -> String?,
        protectedPath: String?,
    ) {
        install(Auth) {
            bearer {
                loadTokens {
                    tokenProvider()?.let { token ->
                        BearerTokens(accessToken = token, refreshToken = null)
                    }
                }

                sendWithoutRequest { request ->
                    request.url.pathSegments.contains(protectedPath)
                }
            }
        }
    }
}