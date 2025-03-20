package plugin.module

import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModuleFeatureBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "convention.library")
            apply(plugin = "convention.library.compose")
            apply(plugin = "dependency.koin")

            dependencies {
                "implementation"(project(":common:base:feature"))
                "implementation"(project(":common:base:model"))

                "implementation"(project(":common:core:validation"))
                "implementation"(project(":common:core:navigation"))
                "implementation"(project(":common:core:toast"))
                "implementation"(project(":common:core:architecture"))
                "implementation"(project(":common:core:date"))
                "implementation"(project(":common:core:file"))
                "implementation"(project(":common:core:design:compound"))

                "implementation"(project(":common:business:model:global"))
                /**==============================================================================**/
                // koinViewModel
                "implementation"(libs.findLibrary("koin.androidx.compose").get())

                //coil
                "implementation"(libs.findLibrary("coil-compose-core").get())
            }
        }
    }
}
