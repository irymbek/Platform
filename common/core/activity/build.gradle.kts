plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.dependency.koin)
    alias(platformLibs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.core.activity"

}

dependencies {
    api(projects.common.core.file)

    //koin inject
    implementation(platformLibs.koin.androidx.compose)

    //rememberLauncherForActivityResult
    implementation(platformLibs.androidx.activity.compose)
}