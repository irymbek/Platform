package plugin.base

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class BaseDataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.convention.library)
            applyPlugin(libs.plugins.dependency.koin)

            dependencies {
                implementation(":common:base:data")
                implementation(":common:core:architecture")
                implementation(":common:core:date")
                implementation(":common:core:file")
                implementation(":common:business:model:global")
                /**==============================================================================**/
                implementation(libs.androidx.paging.common)
            }
        }
    }
}