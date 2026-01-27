plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.dependency.koin)
}

android {
    namespace = "kz.rymbek.platform.common.core.player.playback"
}

dependencies {
    api(platformLibs.androidx.media3.exoplayer)
    implementation(platformLibs.androidx.media3.session)
    implementation(platformLibs.kotlinx.coroutines.core)
}