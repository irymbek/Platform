plugins {
    alias(platformLibs.plugins.module.platform.model.ui)
    alias(platformLibs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.business.model.ui"
}

dependencies {
    implementation(projects.platform.common.base.feature)

    implementation(platform(platformLibs.androidx.compose.bom))
    implementation(platformLibs.androidx.compose.material3)
    implementation(platformLibs.androidx.compose.ui.graphics)
}