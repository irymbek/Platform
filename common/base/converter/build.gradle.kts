plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.dependency.serialization)
}

android {
    namespace = "kz.rymbek.platform.common.base.converter"
}

dependencies {
    implementation(libs.androidx.room.runtime)
}