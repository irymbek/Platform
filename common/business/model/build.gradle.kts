plugins {
    alias(platformLibs.plugins.module.platform.model)
}

android {
    namespace = "kz.rymbek.platform.common.business.model"
}

dependencies {
    implementation(projects.platform.common.base.feature)
}