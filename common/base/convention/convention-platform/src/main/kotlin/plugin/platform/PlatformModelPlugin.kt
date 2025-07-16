package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementations
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformModelPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val modules = setOf(
                "common:base:model",
                "common:core:architecture",
                "common:core:date",
            )
            val paths = contextPrefix(
                modules
            )

            applyPlugin(platformLibs.plugins.convention.library)

            dependencies {
                implementations(paths)
            }
        }
    }
}