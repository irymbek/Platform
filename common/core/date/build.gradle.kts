plugins {
    alias(platformLibs.plugins.build.library)
}

android {
    namespace = "kz.rymbek.platform.common.core.date"
}

dependencies {
    api(platformLibs.kotlinx.datetime)
}
