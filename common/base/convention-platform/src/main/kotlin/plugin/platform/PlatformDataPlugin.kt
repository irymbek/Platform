package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            dependencies {
                implementation(":platform:common:base:database")
                implementation(":platform:common:base:data")
                implementation(":platform:common:base:model")

                implementation(":platform:common:core:architecture")
                implementation(":platform:common:core:date")

                implementation(":platform:common:business:model")
                /**==============================================================================**/
                implementation(platformLibs.androidx.paging.common)
                implementation(platformLibs.androidx.room.ktx)
            }
        }
    }
}