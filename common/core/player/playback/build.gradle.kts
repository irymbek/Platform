plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.core.player.playback"
}

dependencies {
    implementation(platformLibs.androidx.media3.exoplayer)
    implementation(platformLibs.androidx.media3.session)
}