plugins {
    alias(libs.plugins.module.base.converter)
}

android {
    namespace = "kz.rymbek.platform.common.business.converter"
}

dependencies {
    //Use the KTX extension function String.toUri
    implementation(libs.androidx.core.ktx)
}