package plugin.dependency

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import kz.rymbek.platform.common.base.convention.ksp
import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DependencyKoinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.ksp)

            dependencies {
                ksp(libs.koin.ksp.compiler)
                implementation(libs.koin.annotations)
                implementation(libs.koin.core)
            }
        }
    }
}