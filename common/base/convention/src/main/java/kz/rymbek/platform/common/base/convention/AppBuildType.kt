package kz.rymbek.platform.common.base.convention

enum class AppBuildType(
    val applicationIdSuffix: String? = null,
) {
    DEBUG(applicationIdSuffix = ".debug"),
    RELEASE,
}