package kz.rymbek.platform.common.base.network.base_api

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

@Serializable
internal data class ApiErrorModel(
    val message: String? = null,
    val errors: JsonElement? = null
)

internal fun ApiErrorModel.toUserMessage(): String {
    if (!message.isNullOrBlank()) return message

    errors?.let { element ->
        return when {
            element is JsonArray -> {
                element.mapNotNull { it.jsonPrimitive.contentOrNull }.joinToString("\n")
            }
            element is JsonObject -> {
                element.values
                    .filterIsInstance<JsonArray>()
                    .flatMap { array -> array.mapNotNull { it.jsonPrimitive.contentOrNull } }
                    .joinToString("\n")
            }
            else -> null
        } ?: "Произошла неизвестная ошибка"
    }

    return "Произошла неизвестная ошибка"
}