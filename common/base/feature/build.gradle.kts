plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.feature"
}

dependencies {
    //viewModel support
    implementation(libs.androidx.lifecycle.viewmodelKtx)

    //PagingData
    implementation(libs.androidx.paging.common)

    //rememberLauncherForActivityResult
    implementation(libs.androidx.activity.compose)
}