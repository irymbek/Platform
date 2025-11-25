package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformNetworkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.dependency.koin)
            applyPlugin(platformLibs.plugins.dependency.serialization)

            /*extensions.configure<KspExtension> {
                arg("KOIN_DEFAULT_MODULE", "false")
            }*/

            dependencies {
                implementation(":platform:common:base:network")

                implementation(":platform:common:core:architecture")

                implementation(":platform:common:business:model:ui")
                /**==============================================================================**/
                implementation(platformLibs.kotlinx.datetime)

                implementation(platformLibs.ktor.client.core)
                implementation(platformLibs.ktor.client.resources)
            }
        }
    }
}