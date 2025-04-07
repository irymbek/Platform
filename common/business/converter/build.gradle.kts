plugins {
    alias(libs.plugins.module.platform.converter)
}

android {
    namespace = "kz.rymbek.platform.common.business.converter"
}

dependencies {
    //Use the KTX extension function String.toUri
    implementation(libs.androidx.core.ktx)
}