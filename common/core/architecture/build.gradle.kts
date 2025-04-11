plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.core.architecture"
}

dependencies {
    implementation(platformLibs.kotlinx.coroutines.core)
}