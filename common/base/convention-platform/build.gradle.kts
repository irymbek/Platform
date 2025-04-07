import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "kz.rymbek.platform.common.base.convention"

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.java.get())
private val projectJvmTarget: JvmTarget = JvmTarget.fromTarget(libs.versions.java.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}

kotlin {
    compilerOptions {
        jvmTarget = projectJvmTarget
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.room.gradlePlugin)
    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("JvmPlugin") {
            id = libs.plugins.convention.jvm.get().pluginId
            implementationClass = "plugin.convention.jvm.JvmPlugin"
        }
        /*========================================================================================*/
        register("ApplicationPlugin") {
            id = libs.plugins.convention.application.asProvider().get().pluginId
            implementationClass = "plugin.convention.application.ApplicationPlugin"
        }
        register("ApplicationComposePlugin") {
            id = libs.plugins.convention.application.compose.get().pluginId
            implementationClass = "plugin.convention.application.ApplicationComposePlugin"
        }
        /*========================================================================================*/
        register("LibraryPlugin") {
            id = libs.plugins.convention.library.asProvider().get().pluginId
            implementationClass = "plugin.convention.library.LibraryPlugin"
        }
        register("LibraryComposePlugin") {
            id = libs.plugins.convention.library.compose.get().pluginId
            implementationClass = "plugin.convention.library.LibraryComposePlugin"
        }
        /**======================================================================================**/
        register("DependencyKoinPlugin") {
            id = libs.plugins.dependency.koin.get().pluginId
            implementationClass = "plugin.dependency.DependencyKoinPlugin"
        }
        register("DependencyRoomPlugin") {
            id = libs.plugins.dependency.room.get().pluginId
            implementationClass = "plugin.dependency.DependencyRoomPlugin"
        }
        register("DependencySerialization") {
            id = libs.plugins.dependency.serialization.get().pluginId
            implementationClass = "plugin.dependency.DependencySerialization"
        }
        /**======================================================================================**/
        register("BaseConverterPlugin") {
            id = libs.plugins.module.platform.converter.get().pluginId
            implementationClass = "plugin.platform.PlatformConverterPlugin"
        }
        register("BaseDatabasePlugin") {
            id = libs.plugins.module.platform.database.get().pluginId
            implementationClass = "plugin.platform.PlatformDatabasePlugin"
        }
        register("BaseDataPlugin") {
            id = libs.plugins.module.platform.data.get().pluginId
            implementationClass = "plugin.platform.PlatformDataPlugin"
        }
        register("BaseDataStorePlugin") {
            id = libs.plugins.module.platform.datastore.get().pluginId
            implementationClass = "plugin.platform.PlatformDataStorePlugin"
        }
        register("BaseDomainPlugin") {
            id = libs.plugins.module.platform.domain.get().pluginId
            implementationClass = "plugin.platform.PlatformDomainPlugin"
        }
        register("BaseFeaturePlugin") {
            id = libs.plugins.module.platform.feature.get().pluginId
            implementationClass = "plugin.platform.PlatformFeaturePlugin"
        }
        register("BaseModelPlugin") {
            id = libs.plugins.module.platform.model.get().pluginId
            implementationClass = "plugin.platform.PlatformModelPlugin"
        }
        register("BaseNetworkPlugin") {
            id = libs.plugins.module.platform.network.get().pluginId
            implementationClass = "plugin.platform.PlatformNetworkPlugin"
        }
        register("BaseWorkPlugin") {
            id = libs.plugins.module.platform.work.get().pluginId
            implementationClass = "plugin.platform.BaseWorkPlugin"
        }
    }
}