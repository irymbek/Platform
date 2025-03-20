package plugin.module

import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModuleDomainBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "convention.library")
            apply(plugin = "dependency.koin")

            dependencies {
                "implementation"(project(":common:core:architecture"))
                /**==============================================================================**/
                "implementation"(libs.findLibrary("kotlinx-datetime").get())
                "implementation"(libs.findLibrary("androidx-paging-common").get())
            }
        }
    }
}