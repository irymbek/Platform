package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.implementations
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDataStorePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            val modules = setOf(
                "common:base:datastore",
                "common:base:model",
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
                implementation(platformLibs.androidx.dataStore.preferences)
            }
        }
    }
}