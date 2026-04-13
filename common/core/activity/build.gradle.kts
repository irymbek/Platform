plugins {
    alias(platformLibs.plugins.build.library)
    alias(platformLibs.plugins.build.library.compose)
    alias(platformLibs.plugins.dependency.koin)
}

android {
    namespace = "kz.rymbek.platform.common.core.activity"

}

dependencies {
    api(projects.platform.common.core.file)

    //koin inject
    implementation(platformLibs.koin.androidx.compose)

    //rememberLauncherForActivityResult
    implementation(platformLibs.androidx.activity.compose)
}
