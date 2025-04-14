package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDomainPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            dependencies {
                implementation(":platform:common:core:architecture")
                /**==============================================================================**/
                implementation(platformLibs.kotlinx.datetime)
                implementation(platformLibs.androidx.paging.common)
            }
        }
    }
}