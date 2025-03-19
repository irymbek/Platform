package plugin.module.base

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModuleWorkBasePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "convention.library")

            dependencies {
                "implementation"(project(":common:core:architecture"))
                "implementation"(project(":common:core:date"))
            }
        }
    }
}