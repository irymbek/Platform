package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.implementations
import kz.rymbek.platform.common.base.convention.extensions.ksp
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformModelPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.ksp)

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

                implementation(platformLibs.komm.annotations)
                ksp(platformLibs.komm.processor)
                ksp(platformLibs.komm.plugins.enum)
            }
        }
    }
}