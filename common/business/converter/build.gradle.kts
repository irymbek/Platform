plugins {
    alias(platformLibs.plugins.layer.platform.converter)
}

android {
    namespace = "kz.rymbek.platform.common.business.converter"
}

dependencies {
    //Use the KTX extension function String.toUri
    implementation(platformLibs.androidx.core.ktx)
}
