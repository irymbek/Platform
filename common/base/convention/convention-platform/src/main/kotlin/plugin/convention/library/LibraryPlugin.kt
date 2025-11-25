package plugin.convention.library

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import kz.rymbek.platform.common.base.convention.configureKotlinAndroid
import kz.rymbek.platform.common.base.convention.disableAllTests
import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(platformLibs.plugins.android.library)
            applyPlugin(platformLibs.plugins.kotlin)

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = platformLibs.versions.targetSdk.get().toInt()
                testOptions.animationsDisabled = true
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                disableAllTests()
            }
        }
    }
}