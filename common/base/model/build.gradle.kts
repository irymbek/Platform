plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.model"
}

dependencies {
    implementation(platformLibs.konform.jvm)
}