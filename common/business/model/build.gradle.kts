plugins {
    alias(platformLibs.plugins.convention.library)
    //alias(platformLibs.plugins.module.platform.model)
}

android {
    namespace = "kz.rymbek.platform.common.business.model"
}

dependencies {
    implementation(projects.platform.common.base.feature)
    implementation(projects.platform.common.base.model)
}