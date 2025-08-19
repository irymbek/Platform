package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.implementations
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            val modules = setOf(
                "common:base:database",
                "common:base:data",
                "common:base:model",
                "common:core:architecture",
                "common:core:date",
                "common:business:model:ui",
            )
            val paths = contextPrefix(
                modules
            )

            dependencies {
                implementations(
                    paths
                )
                /**==============================================================================**/
                implementation(platformLibs.androidx.paging.common)
                implementation(platformLibs.androidx.room.ktx)
            }
        }
    }
}