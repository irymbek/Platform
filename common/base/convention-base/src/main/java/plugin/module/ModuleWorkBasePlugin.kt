package plugin.module

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ModuleWorkBasePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.convention.library.asProvider())

            dependencies {
                implementation(":common:core:architecture")
                implementation(":common:core:date")
            }
        }
    }
}