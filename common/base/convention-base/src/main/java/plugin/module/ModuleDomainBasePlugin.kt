package plugin.module

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ModuleDomainBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.convention.library.asProvider())
            applyPlugin(libs.plugins.dependency.koin)

            dependencies {
                implementation(":common:core:architecture")
                /**==============================================================================**/
                implementation(libs.kotlinx.datetime)
                implementation(libs.androidx.paging.common)
            }
        }
    }
}