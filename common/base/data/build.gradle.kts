plugins {
    alias(platformLibs.plugins.build.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.data"
}

dependencies {
    implementation(projects.platform.common.base.pagination)

    implementation(platformLibs.androidx.paging.common)
}
