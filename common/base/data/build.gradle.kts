plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.data"
}

dependencies {
    api(projects.common.base.database)
    implementation(platformLibs.androidx.paging.common)
}