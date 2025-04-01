plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "kz.uso.krl.common.base.network"
}

dependencies {
    implementation(projects.common.core.architecture)

    implementation(libs.ktor.client.resources)

    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
}