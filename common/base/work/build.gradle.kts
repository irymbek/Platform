plugins {
    alias(platformLibs.plugins.build.library)
}


android {
    namespace = "kz.rymbek.platform.common.base.work"
}

dependencies {
    implementation(projects.platform.common.core.architecture)
    
    implementation(platformLibs.androidx.work.runtime)
}
