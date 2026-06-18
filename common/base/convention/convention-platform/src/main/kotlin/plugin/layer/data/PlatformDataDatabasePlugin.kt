package plugin.layer.data

import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.contextPrefix
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PlatformDataDatabasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.build.library)
            applyPlugin(platformLibs.plugins.dependency.koin)

            val path = contextPrefix(
                "common:base:database",
            )

            dependencies {
                implementation(path)
                /**==============================================================================**/
                implementation(platformLibs.androidx.room.ktx)
                implementation(platformLibs.androidx.paging.common)
            }
        }
    }
}