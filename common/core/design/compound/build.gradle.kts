plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
}


android {
    namespace = "kz.rymbek.platform.common.core.design.compound"
}

dependencies {
    implementation(projects.common.core.architecture)
    implementation(projects.common.base.model)
    implementation(projects.common.base.feature)

    api(projects.common.core.design.foundation)

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    //Preview for video
    implementation(platform(libs.coil.bom))
    implementation(libs.coil.compose)
    implementation(libs.coil.video)

    //paging3 support
    implementation(libs.androidx.paging.compose)
}