package plugin.platform

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformNetworkPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.convention.library)
            applyPlugin(libs.plugins.dependency.koin)
            applyPlugin(libs.plugins.dependency.serialization)

            dependencies {
                implementation(":common:base:network")
                implementation(":common:core:architecture")
                implementation(":common:core:file")
                implementation(":common:business:model:global")
                /**==============================================================================**/
                implementation(libs.kotlinx.datetime)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.resources)
            }
        }
    }
}