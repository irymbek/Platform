plugins {
    alias(platformLibs.plugins.build.library)
}

android {
    namespace = "kz.rymbek.platform.common.core.architecture"
}

dependencies {
    implementation(platformLibs.kotlinx.coroutines.core)
}
