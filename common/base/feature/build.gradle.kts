plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.feature"
}

dependencies {
    //viewModel support
    implementation(platformLibs.androidx.lifecycle.viewmodelKtx)

    //PagingData
    implementation(platformLibs.androidx.paging.common)

    //rememberLauncherForActivityResult
    implementation(platformLibs.androidx.activity.compose)
}