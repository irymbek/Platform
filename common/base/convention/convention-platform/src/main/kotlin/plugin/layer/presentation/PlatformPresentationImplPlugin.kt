package plugin.layer.presentation

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformPresentationImplPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.build.library)
            applyPlugin(platformLibs.plugins.build.library.compose)
            applyPlugin(platformLibs.plugins.dependency.koin)
            applyPlugin(platformLibs.plugins.dependency.serialization)

            dependencies {
                implementation(":platform:common:base:feature")
                implementation(":platform:common:base:model")
                implementation(":platform:common:base:navigation")

                implementation(":platform:common:core:architecture")
                implementation(":platform:common:core:date")
                implementation(":platform:common:core:design:compound")

                implementation(":platform:common:business:model:ui")
                /**==============================================================================**/
                // koinViewModel
                implementation(platformLibs.koin.androidx.compose)

                //Navigation support
                implementation(platformLibs.koin.compose.navigation3)

                //coil
                implementation(platformLibs.coil.compose.core)

                implementation(platformLibs.androidx.lifecycle.viewmodel.navigation3)

                implementation(platformLibs.orbit.compose)

                //validation
                implementation(platformLibs.konform.jvm)
            }
        }
    }
}