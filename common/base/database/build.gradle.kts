plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.database"
}

dependencies {
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.room.ktx)
}