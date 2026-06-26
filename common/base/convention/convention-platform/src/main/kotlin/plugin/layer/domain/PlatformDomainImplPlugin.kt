package plugin.layer.domain

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDomainImplPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.build.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            val modules = setOf(
                "common:base:model",
                "common:core:architecture",
            )

            dependencies {
                implementation(contextPrefix(modules))
                /**==============================================================================**/
                implementation(platformLibs.kotlinx.datetime)
                implementation(platformLibs.androidx.paging.common)
            }
        }
    }
}