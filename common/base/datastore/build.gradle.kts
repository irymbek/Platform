plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.rymbek.platform.common.base.datastore"
}

dependencies {
    dependencies {
        //shared preference extension
        implementation(platformLibs.androidx.core.ktx)

        implementation(platformLibs.androidx.dataStore.preferences)
    }
}