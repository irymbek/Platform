package plugin.convention.jvm

import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.configureKotlinJvm
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(libs.plugins.kotlin.jvm)
            configureKotlinJvm()
        }
    }
}
