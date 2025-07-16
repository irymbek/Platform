package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformWorkPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            dependencies {
                implementation(":platform:common:base:database")
                implementation(":platform:common:base:model") //Identifiable
                implementation(":platform:common:base:work")

                implementation(":platform:common:core:architecture")

                /**==============================================================================**/
                //WorkManager
                implementation(platformLibs.androidx.work.runtime)
                //DI
                implementation(platformLibs.koin.androidx.workmanager)
            }
        }
    }
}