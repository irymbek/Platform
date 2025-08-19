plugins {
    alias(platformLibs.plugins.module.platform.model)
}

android {
    namespace = "kz.rymbek.platform.common.business.model.cache"
}

dependencies {
    implementation(platformLibs.androidx.room.runtime)
}