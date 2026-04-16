package plugin.layer.domain

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
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

            dependencies {
                implementation(":platform:common:base:model")

                implementation(":platform:common:core:architecture")
                /**==============================================================================**/
                implementation(platformLibs.kotlinx.datetime)
                implementation(platformLibs.androidx.paging.common)
            }
        }
    }
}