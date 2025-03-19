package kz.rymbek.platform.convention

enum class AppBuildType(
    val applicationIdSuffix: String? = null,
) {
    DEBUG(applicationIdSuffix = ".debug"),
    RELEASE,
}