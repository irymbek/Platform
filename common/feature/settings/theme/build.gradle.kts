plugins {
    alias(platformLibs.plugins.module.platform.feature)
}

android {
    namespace = "kz.rymbek.platform.common.feature.settings.theme"
}

dependencies {
    implementation(projects.platform.common.business.data.app)
}