package plugin.dependency

import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class DependencyRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "androidx.room")
            apply(plugin = "com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                arg("room.generateKotlin", "true")
            }
            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.room.ktx").get())
                "ksp"(libs.findLibrary("androidx.room.compiler").get())
                "implementation"(libs.findLibrary("androidx.room.paging").get())
            }
        }
    }
}