plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.datastore"
}

dependencies {
    dependencies {
        //shared preference extension
        implementation(libs.androidx.core.ktx)

        implementation(libs.androidx.dataStore.preferences)
    }
}