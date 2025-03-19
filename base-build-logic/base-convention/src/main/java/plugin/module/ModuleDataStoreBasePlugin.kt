package plugin.module

import kz.rymbek.platform.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModuleDataStoreBasePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply(plugin = "convention.library")
            apply(plugin = "dependency.koin")

            dependencies {
                "implementation"(project(":common:base:datastore"))
                "implementation"(project(":common:core:architecture"))
                "implementation"(project(":common:business:model:global"))
                /**==============================================================================**/
                "implementation"(libs.findLibrary("androidx-dataStore-preferences").get())
            }
        }
    }
}