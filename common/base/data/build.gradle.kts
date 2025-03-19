plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.data"
}

dependencies {
    api(projects.common.base.database)
    implementation(libs.androidx.paging.common)
}