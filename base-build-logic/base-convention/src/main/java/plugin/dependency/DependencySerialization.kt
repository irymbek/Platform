package plugin.dependency

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import kz.rymbek.platform.convention.libs

class DependencySerialization : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "kotlinx-serialization")

            dependencies {
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
            }
        }
    }
}