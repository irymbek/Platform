package plugin.convention.jvm

import kz.rymbek.platform.common.base.convention.configureKotlinJvm
import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.kotlin.jvm)
            configureKotlinJvm()
        }
    }
}
