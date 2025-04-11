plugins {
    alias(platformLibs.plugins.convention.library)
}

android {
    namespace = "kz.uso.krl.common.base.network"
}

dependencies {
    implementation(projects.common.core.architecture)

    implementation(platformLibs.ktor.client.resources)

    implementation(platformLibs.ktor.client.okhttp)
    implementation(platformLibs.ktor.client.content.negotiation)
    implementation(platformLibs.ktor.client.logging)
    implementation(platformLibs.ktor.serialization.kotlinx.json)
}