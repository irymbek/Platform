plugins {
    alias(platformLibs.plugins.build.library)
    //image vector
    alias(platformLibs.plugins.build.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.base.app"
}

dependencies {
    implementation(platformLibs.androidx.compose.material3)

    //NavKey
    implementation(platformLibs.androidx.navigation3.runtime)
}
