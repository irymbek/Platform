package kz.rymbek.platform.common.business.model.enums

enum class MediaType(
    val prefix: String,
    val extension: String,
    val mimeType: String,
) {
    IMAGE(
        prefix = "photo",
        extension = "jpg",
        mimeType = "image/jpeg",
    ),
    VIDEO(
        prefix = "video",
        extension = "mp4",
        mimeType = "video/mp4",
    ),
    UNDEFINED(
        prefix = "",
        extension = "",
        mimeType = "",
    );
}
