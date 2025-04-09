package plugin.dependency

import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.ksp
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class DependencyRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.room)
            applyPlugin(platformLibs.plugins.ksp)

            extensions.configure<KspExtension> {
                arg("room.generateKotlin", "true")
            }
            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                implementation(platformLibs.androidx.room.ktx)
                ksp(platformLibs.androidx.room.compiler)
                implementation(platformLibs.androidx.room.paging)
            }
        }
    }
}