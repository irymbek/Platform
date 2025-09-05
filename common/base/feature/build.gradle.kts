plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.feature"
}

dependencies {
    implementation(projects.platform.common.core.validation)

    //viewModel support
    implementation(platformLibs.androidx.lifecycle.viewmodelKtx)
    implementation(platformLibs.orbit.core)

    //PagingData
    implementation(platformLibs.androidx.paging.common)
}