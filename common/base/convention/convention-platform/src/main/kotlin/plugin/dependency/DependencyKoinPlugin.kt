package plugin.dependency

import com.google.devtools.ksp.gradle.KspExtension
import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.ksp
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class DependencyKoinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.ksp)

            extensions.configure<KspExtension> {
                //arg("KOIN_CONFIG_CHECK","true")
                arg("KOIN_DEFAULT_MODULE", "true")
            }

            dependencies {
                ksp(platformLibs.koin.ksp.compiler)
                implementation(platformLibs.koin.annotations)
                implementation(platformLibs.koin.core)
            }
        }
    }
}