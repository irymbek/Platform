package plugin.module.base

import kz.rymbek.platform.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModuleNetworkBasePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "convention.library")
            apply(plugin = "dependency.koin")
            apply(plugin = "dependency.serialization")

            dependencies {
                "implementation"(project(":common:base:network"))
                "implementation"(project(":common:core:architecture"))
                "implementation"(project(":common:core:file"))
                "implementation"(project(":common:business:model:global"))
                /**==============================================================================**/
                "implementation"(libs.findLibrary("kotlinx-datetime").get())

                "implementation"(libs.findLibrary("ktor-client-core").get())
                "implementation"(libs.findLibrary("ktor-client-resources").get())
            }
        }
    }
}