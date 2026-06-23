package plugin.layer

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformNetworkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.build.library)
            applyPlugin(platformLibs.plugins.dependency.koin)
            applyPlugin(platformLibs.plugins.dependency.serialization)

            val modules = setOf(
                ":common:base:network",
                ":common:core:architecture",
                ":common:business:model:ui",
            )

            dependencies {
                implementation(contextPrefix(modules))
                /**==============================================================================**/
                implementation(platformLibs.kotlinx.datetime)

                implementation(platformLibs.ktor.client.core)
                implementation(platformLibs.ktor.client.resources)
            }
        }
    }
}