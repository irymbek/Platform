plugins {
    alias(platformLibs.plugins.build.library)
    alias(platformLibs.plugins.build.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.base.navigation"
}

dependencies {
    implementation(platformLibs.androidx.navigation3.runtime)
    implementation(platformLibs.androidx.lifecycle.viewmodel.navigation3)
}
