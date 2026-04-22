package plugin.layer.model

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementations
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformModelBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.build.library)

            val modules = setOf(
                "common:base:model",
                "common:core:architecture",
                "common:core:date",
            )
            val paths = contextPrefix(
                modules
            )

            dependencies {
                implementations(paths)
            }
        }
    }
}