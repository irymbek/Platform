plugins {
    alias(libs.plugins.convention.library)
    //image vector
    alias(libs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.base.app"
}

dependencies {
    implementation(libs.androidx.compose.material3)
}