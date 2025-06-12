package kz.rymbek.platform.common.business.model.enums

enum class MediaType(
    val prefix: String,
) {
    IMAGE(
        prefix = "photo",
    ),
    VIDEO(
        prefix = "video",
    ),
}
