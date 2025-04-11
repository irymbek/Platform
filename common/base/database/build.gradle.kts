plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.database"
}

dependencies {
    implementation(platformLibs.androidx.paging.common)
    implementation(platformLibs.androidx.room.ktx)
}