plugins {
    alias(libs.plugins.convention.library)
}


android {
    namespace = "kz.rymbek.platform.common.base.work"
}

dependencies {
    implementation(libs.androidx.work.runtime)
}