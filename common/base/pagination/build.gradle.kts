plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.aniliberty.project.platform.common.base.pagination"
}

dependencies {
    implementation(platformLibs.androidx.paging.common)
}