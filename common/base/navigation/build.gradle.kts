plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.base.navigation"
}

dependencies {
    implementation(platformLibs.androidx.navigation.runtime.ktx)
    implementation(platformLibs.androidx.navigation.compose)
}