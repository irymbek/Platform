package plugin.convention.library

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import kz.rymbek.platform.convention.configureKotlinAndroid
import kz.rymbek.platform.convention.disableAllTests

class LibraryPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")

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