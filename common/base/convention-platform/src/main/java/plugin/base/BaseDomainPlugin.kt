package plugin.base

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class BaseDomainPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.convention.library)
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