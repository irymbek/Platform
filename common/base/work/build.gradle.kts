plugins {
    alias(platformLibs.plugins.convention.library)
}


android {
    namespace = "kz.rymbek.platform.common.base.work"
}

dependencies {
    implementation(platformLibs.androidx.work.runtime)
}