plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.data"
}

dependencies {
    implementation(platformLibs.androidx.paging.common)
}