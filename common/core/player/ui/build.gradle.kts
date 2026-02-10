plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.convention.library.compose)
}

android {
    namespace = "kz.rymbek.platform.common.core.player.ui"
}

dependencies {
    implementation(projects.platform.common.core.activity)
    implementation(projects.platform.common.core.design.foundation)

    implementation(platformLibs.androidx.media3.ui.compose.material3)

    //BackHandler
    implementation(platformLibs.androidx.activity.compose)
}