package plugin.platform

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDataStorePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(platformLibs.plugins.convention.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            dependencies {
                implementation(":platform:common:base:datastore")
                implementation(":platform:common:core:architecture")
                implementation(":platform:common:business:model")
                /**==============================================================================**/
                implementation(platformLibs.androidx.dataStore.preferences)
            }
        }
    }
}