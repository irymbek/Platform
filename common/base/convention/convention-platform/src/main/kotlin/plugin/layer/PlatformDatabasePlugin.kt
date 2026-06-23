package plugin.layer

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDatabasePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(platformLibs.plugins.build.library)
            applyPlugin(platformLibs.plugins.dependency.room)
            applyPlugin(platformLibs.plugins.dependency.koin)

            val modules = setOf(
                "common:base:database",
                "common:base:converter",
                "common:base:model",
                "common:core:architecture",
                "common:business:converter",
                "common:business:model:cache",
            )

            dependencies {
                implementation(contextPrefix(modules))
                /**==============================================================================**/
                implementation(platformLibs.kotlinx.datetime)
            }
        }
    }
}