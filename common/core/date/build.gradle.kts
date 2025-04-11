plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.core.date"
}

dependencies {
    api(platformLibs.kotlinx.datetime)
}