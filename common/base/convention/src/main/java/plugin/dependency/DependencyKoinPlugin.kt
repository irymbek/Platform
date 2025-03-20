package plugin.dependency

import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class DependencyKoinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.devtools.ksp")

            dependencies {
                "ksp"(libs.findLibrary("koin.ksp.compiler").get())
                "implementation"(libs.findLibrary("koin.annotations").get())
                "implementation"(libs.findLibrary("koin.core").get())
            }
        }
    }
}