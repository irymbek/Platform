plugins {
    alias(platformLibs.plugins.layer.platform.model.cache)
}

android {
    namespace = "kz.rymbek.platform.common.business.model.cache"
}

dependencies {
    implementation(platformLibs.androidx.room.runtime)
}
