plugins {
    alias(platformLibs.plugins.module.platform.data)
}

android {
    namespace = "kz.rymbek.platform.common.business.data.app"
}

dependencies {
    implementation(projects.platform.common.business.datastore)
}