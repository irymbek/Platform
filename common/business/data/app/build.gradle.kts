plugins {
    alias(platformLibs.plugins.layer.data.impl)
}

android {
    namespace = "kz.rymbek.platform.common.business.data.app"
}

dependencies {
    implementation(projects.platform.common.business.datastore)
}
