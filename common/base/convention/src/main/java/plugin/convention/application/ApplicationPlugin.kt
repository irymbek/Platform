package plugin.convention.application

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import kz.rymbek.platform.common.base.convention.AppBuildType
import kz.rymbek.platform.common.base.convention.configureKotlinAndroid
import kz.rymbek.platform.common.base.convention.disableAllTests
import kz.rymbek.platform.common.base.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class ApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "convention.application.compose")
            apply(plugin = "dependency.koin")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35

                buildFeatures.buildConfig = true

                defaultConfig {
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }

                androidResources {
                    localeFilters += listOf("ru", "en")
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
                        applicationIdSuffix = AppBuildType.RELEASE.applicationIdSuffix
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                    debug {
                        applicationIdSuffix = AppBuildType.DEBUG.applicationIdSuffix
                    }
                }

                packaging {
                    resources {
                        excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                    }
                }

                extensions.extraProperties.set("ksp.args", mapOf(
                    "KOIN_CONFIG_CHECK" to "true",
                    "KOIN_USE_COMPOSE_VIEWMODEL" to "true"
                ))

                extensions.configure<ApplicationAndroidComponentsExtension> {
                    disableAllTests()
                    onVariants { variant ->
                        val variantName = variant.name
                        project.extensions.configure<ApplicationExtension> {
                            sourceSets {
                                getByName("main") {
                                    java.srcDir(File("build/generated/ksp/$variantName/kotlin"))
                                }
                            }
                        }
                    }
                }
            }

            dependencies {
                "implementation"(project(":common:base:app"))
                "implementation"(project(":common:base:feature"))
                "implementation"(project(":common:core:architecture"))
                "implementation"(project(":common:core:design:foundation"))
                "implementation"(project(":common:core:navigation"))
                "implementation"(project(":common:business:datastore"))
                "implementation"(project(":common:business:model:global"))

                /**==============================================================================**/
                "implementation"(platform(libs.findLibrary("coil-bom").get()))
                "implementation"(libs.findLibrary("coil").get())
                "implementation"(libs.findLibrary("coil.video").get())

                // ComponentActivity, setContent, enableEdgeToEdge
                "implementation"(libs.findLibrary("androidx.activity.compose").get())

                // Splash screen
                "implementation"(libs.findLibrary("androidx.core.splashscreen").get())

                // Navigation
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())

                // DI
                "implementation"(libs.findLibrary("koin.android").get())
                "implementation"(libs.findLibrary("koin.androidx.compose").get())
            }
        }
    }
}