package plugin.platform.model

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformModelRemotePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(platformLibs.plugins.module.platform.model.base)
            applyPlugin(platformLibs.plugins.dependency.serialization)

            dependencies {
                implementation(":platform:common:business:converter")
            }
        }
    }
}