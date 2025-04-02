plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.core.date"
}

dependencies {
    api(libs.kotlinx.datetime)
}