package plugin.module

import kz.rymbek.platform.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModuleDataBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "convention.library")
            apply(plugin = "dependency.koin")

            dependencies {
                "implementation"(project(":common:base:data"))
                "implementation"(project(":common:core:architecture"))
                "implementation"(project(":common:core:date"))
                "implementation"(project(":common:core:file"))
                "implementation"(project(":common:business:model:global"))
                /**==============================================================================**/
                "implementation"(libs.findLibrary("androidx-paging-common").get())
            }
        }
    }
}