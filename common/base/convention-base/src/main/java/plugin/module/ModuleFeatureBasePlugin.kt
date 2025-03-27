package plugin.module

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ModuleFeatureBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.convention.library.asProvider())
            applyPlugin(libs.plugins.convention.library.compose)
            applyPlugin(libs.plugins.dependency.koin)

            dependencies {
                implementation(":common:base:feature")
                implementation(":common:base:model")

                implementation(":common:core:validation")
                implementation(":common:core:navigation")
                implementation(":common:core:toast")
                implementation(":common:core:architecture")
                implementation(":common:core:date")
                implementation(":common:core:file")
                implementation(":common:core:design:compound")

                implementation(":common:business:model:global")
                /**==============================================================================**/
                // koinViewModel
                implementation(libs.koin.androidx.compose)

                //coil
                implementation(libs.coil.compose.core)
            }
        }
    }
}
