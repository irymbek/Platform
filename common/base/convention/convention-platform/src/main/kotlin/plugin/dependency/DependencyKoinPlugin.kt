package plugin.dependency

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DependencyKoinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.koin.compiler)

            dependencies {
                implementation(platformLibs.koin.annotations)
                implementation(platformLibs.koin.core)
            }
        }
    }
}