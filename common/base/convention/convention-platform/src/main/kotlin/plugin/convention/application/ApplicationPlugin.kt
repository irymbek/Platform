package plugin.convention.application

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import kz.rymbek.platform.common.base.convention.AppBuildType
import kz.rymbek.platform.common.base.convention.configureKotlinAndroid
import kz.rymbek.platform.common.base.convention.disableAllTests
import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class ApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(platformLibs.plugins.android.application)
            applyPlugin(platformLibs.plugins.kotlin)
            applyPlugin(platformLibs.plugins.convention.application.compose)
            applyPlugin(platformLibs.plugins.dependency.koin)

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = platformLibs.versions.targetSdk.get().toInt()

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
                implementation(":platform:common:base:app")
                implementation(":platform:common:base:feature")
                implementation(":platform:common:base:navigation")
                implementation(":platform:common:business:data:app")
                implementation(":platform:common:business:datastore")
                implementation(":platform:common:business:model")
                implementation(":platform:common:core:architecture")
                implementation(":platform:common:core:design:foundation")
                implementation(":platform:common:feature:settings:theme")
                /**==============================================================================**/
                implementation(platform(platformLibs.coil.bom))
                implementation(platformLibs.coil.asProvider())
                implementation(platformLibs.coil.video)

                // ComponentActivity, setContent, enableEdgeToEdge
                implementation(platformLibs.androidx.activity.compose)

                // Splash screen
                implementation(platformLibs.androidx.core.splashscreen)

                // Navigation
                implementation(platformLibs.androidx.navigation.compose)

                // DI
                implementation(platformLibs.koin.android)
                implementation(platformLibs.koin.androidx.compose)
                implementation(platformLibs.koin.androidx.startup)
            }
        }
    }
}