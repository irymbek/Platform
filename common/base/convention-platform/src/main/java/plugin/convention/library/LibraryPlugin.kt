package plugin.convention.library

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import kz.rymbek.platform.common.base.convention.applyPlugin
import kz.rymbek.platform.common.base.convention.configureKotlinAndroid
import kz.rymbek.platform.common.base.convention.disableAllTests
import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(libs.plugins.android.library)
            applyPlugin(libs.plugins.kotlin)

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                testOptions.animationsDisabled = true
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                disableAllTests()
            }
        }
    }
}