plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)

    //DI for snackbar
    alias(libs.plugins.dependency.koin)
}

android {
    namespace = "kz.rymbek.platform.common.core.design.foundation"
}

dependencies {
    implementation(projects.common.core.architecture)
    implementation(projects.common.base.model)
    implementation(projects.common.core.date)

    implementation(projects.common.business.model)

    //Coil
    implementation(platform(libs.coil.bom))
    implementation(libs.coil.compose)
    //Load images from network
    implementation(libs.coil.network.ktor3)
    //Preview for video
    implementation(libs.coil.video)

    implementation(libs.androidx.paging.compose)

    api(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.navigation.suite)
    implementation(libs.androidx.compose.material.iconsExtended)
}