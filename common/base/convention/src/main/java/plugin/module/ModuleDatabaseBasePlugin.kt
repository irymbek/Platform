package plugin.module

import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModuleDatabaseBasePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply(plugin = "convention.library")
            apply(plugin = "dependency.room")
            apply(plugin = "dependency.koin")

            dependencies {
                "implementation"(project(":common:base:database"))
                "implementation"(project(":common:core:architecture"))
                "implementation"(project(":common:business:converter"))
                /**==============================================================================**/
                "implementation"(libs.findLibrary("kotlinx-datetime").get())
            }
        }
    }
}