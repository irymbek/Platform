plugins {
    alias(platformLibs.plugins.layer.presentation.impl)
}

android {
    namespace = "kz.rymbek.platform.common.feature.settings.theme"
}

dependencies {
    implementation(projects.platform.common.business.data.app)
}
