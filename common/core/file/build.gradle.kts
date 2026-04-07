plugins {
    alias(platformLibs.plugins.build.library)
    alias(platformLibs.plugins.dependency.koin)
}

android {
    namespace = "kz.rymbek.platform.common.core.file"
}

dependencies {
    //FileProvider
    implementation(platformLibs.androidx.core.ktx)

    implementation(platformLibs.okio)
}
