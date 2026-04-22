package plugin.layer.presentation

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformPresentationApiPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.build.library)
            applyPlugin(platformLibs.plugins.dependency.serialization)

            dependencies {
                implementation(":platform:common:base:navigation")
            }
        }
    }
}