plugins {
    alias(platformLibs.plugins.convention.library)
    //image vector
    alias(platformLibs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.base.app"
}

dependencies {
    implementation(platformLibs.androidx.compose.material3)
}