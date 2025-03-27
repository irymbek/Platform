package plugin.dependency

import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.implementation
import kz.rymbek.platform.common.base.convention.ksp
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class DependencyRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.room)
            applyPlugin(libs.plugins.ksp)

            extensions.configure<KspExtension> {
                arg("room.generateKotlin", "true")
            }
            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                implementation(libs.androidx.room.ktx)
                ksp(libs.androidx.room.compiler)
                implementation(libs.androidx.room.paging)
            }
        }
    }
}