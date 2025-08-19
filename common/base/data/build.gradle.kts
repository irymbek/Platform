plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.data"
}

dependencies {
    implementation(projects.platform.common.base.pagination)

    implementation(platformLibs.androidx.paging.common)
}