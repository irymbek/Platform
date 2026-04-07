plugins {
    alias(platformLibs.plugins.build.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.model"
}

dependencies {
    implementation(platformLibs.konform.jvm)
}
