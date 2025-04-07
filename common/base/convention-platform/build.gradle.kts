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
            id = libs.plugins.module.base.converter.get().pluginId
            implementationClass = "plugin.base.BaseConverterPlugin"
        }
        register("BaseDatabasePlugin") {
            id = libs.plugins.module.base.database.get().pluginId
            implementationClass = "plugin.base.BaseDatabasePlugin"
        }
        register("BaseDataPlugin") {
            id = libs.plugins.module.base.data.get().pluginId
            implementationClass = "plugin.base.BaseDataPlugin"
        }
        register("BaseDataStorePlugin") {
            id = libs.plugins.module.base.datastore.get().pluginId
            implementationClass = "plugin.base.BaseDataStorePlugin"
        }
        register("BaseDomainPlugin") {
            id = libs.plugins.module.base.domain.get().pluginId
            implementationClass = "plugin.base.BaseDomainPlugin"
        }
        register("BaseFeaturePlugin") {
            id = libs.plugins.module.base.feature.get().pluginId
            implementationClass = "plugin.base.BaseFeaturePlugin"
        }
        register("BaseModelPlugin") {
            id = libs.plugins.module.base.model.get().pluginId
            implementationClass = "plugin.base.BaseModelPlugin"
        }
        register("BaseNetworkPlugin") {
            id = libs.plugins.module.base.network.get().pluginId
            implementationClass = "plugin.base.BaseNetworkPlugin"
        }
        register("BaseWorkPlugin") {
            id = libs.plugins.module.base.work.get().pluginId
            implementationClass = "plugin.base.BaseWorkPlugin"
        }
    }
}