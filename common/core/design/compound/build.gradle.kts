plugins {
    alias(platformLibs.plugins.convention.library)
    alias(platformLibs.plugins.convention.library.compose)
}


android {
    namespace = "kz.rymbek.platform.common.core.design.compound"
}

dependencies {
    implementation(projects.common.core.architecture)
    implementation(projects.common.base.model)
    implementation(projects.common.base.feature)

    api(projects.platform.common.core.design.foundation)

    implementation(platformLibs.androidx.media3.exoplayer)
    implementation(platformLibs.androidx.media3.ui)

    //Preview for video
    implementation(platform(platformLibs.coil.bom))
    implementation(platformLibs.coil.compose)
    implementation(platformLibs.coil.video)

    //paging3 support
    implementation(platformLibs.androidx.paging.compose)
}