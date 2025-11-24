plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.base.navigation"
}

dependencies {
    implementation(platformLibs.androidx.navigation3.runtime)
    implementation(platformLibs.androidx.lifecycle.viewmodel.navigation3)
}