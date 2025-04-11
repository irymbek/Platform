plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.dependency.serialization)
}

android {
    namespace = "kz.rymbek.platform.common.base.converter"
}

dependencies {
    implementation(platformLibs.androidx.room.runtime)
}