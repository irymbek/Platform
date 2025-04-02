plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.core.architecture"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}