plugins {
    alias(platformLibs.plugins.build.jvm)
}

/*android {
    namespace = "kz.rymbek.platform.common.core.architecture"
}*/

dependencies {
    implementation(platformLibs.kotlinx.coroutines.core)
}
