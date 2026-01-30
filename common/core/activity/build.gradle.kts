plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.dependency.koin)
    alias(platformLibs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.core.activity"

}

dependencies {
    //koin inject
    implementation(platformLibs.koin.androidx.compose)

    //rememberLauncherForActivityResult
    implementation(platformLibs.androidx.activity.compose)
}