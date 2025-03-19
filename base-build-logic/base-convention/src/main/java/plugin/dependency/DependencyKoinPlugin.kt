package plugin.dependency

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import kz.rymbek.platform.convention.libs

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