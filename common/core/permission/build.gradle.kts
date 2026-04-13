plugins {
    alias(platformLibs.plugins.build.library)
    alias(platformLibs.plugins.build.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.core.permission"
}

dependencies {
    implementation(platformLibs.androidx.core.ktx)

    //AppSnackbarState
    implementation(projects.platform.common.core.design.foundation)

    implementation(projects.platform.common.core.activity)
    implementation(platformLibs.com.google.accompanist.permissions)
}