package kz.rymbek.platform.common.base.network.base_api

import kotlinx.serialization.Serializable

@Serializable
internal data class ApiErrorModel(
    val message: String? = null,
    val errors: Map<String, List<String>>? = null
)

internal fun ApiErrorModel.toUserMessage(): String {
    if (!errors.isNullOrEmpty()) {
        return errors.values.flatten().joinToString("\n")
    }

    if (!message.isNullOrBlank()) {
        return message
    }

    return "Произошла неизвестная ошибка"
}