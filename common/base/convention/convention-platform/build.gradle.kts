//import kz.rymbek.platform.common.base.convention.extensions.conventionPlugin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "kz.rymbek.platform.common.base.convention"

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(platformLibs.versions.java.get())
private val projectJvmTarget: JvmTarget = JvmTarget.fromTarget(platformLibs.versions.java.get())

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
    compileOnly(platformLibs.android.gradlePlugin)
    compileOnly(platformLibs.compose.gradlePlugin)
    compileOnly(platformLibs.kotlin.gradlePlugin)
    compileOnly(platformLibs.ksp.gradlePlugin)
    compileOnly(platformLibs.room.gradlePlugin)
    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(platformLibs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(files(projectLibs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        /*conventionPlugin(

        )*/
        register("JvmPlugin") {
            id = platformLibs.plugins.convention.jvm.get().pluginId
            implementationClass = "plugin.convention.jvm.JvmPlugin"
        }
        /*========================================================================================*/
        register("ApplicationPlugin") {
            id = platformLibs.plugins.convention.application.asProvider().get().pluginId
            implementationClass = "plugin.convention.application.ApplicationPlugin"
        }
        register("ApplicationComposePlugin") {
            id = platformLibs.plugins.convention.application.compose.get().pluginId
            implementationClass = "plugin.convention.application.ApplicationComposePlugin"
        }
        /*========================================================================================*/
        register("LibraryPlugin") {
            id = platformLibs.plugins.convention.library.asProvider().get().pluginId
            implementationClass = "plugin.convention.library.LibraryPlugin"
        }
        register("LibraryComposePlugin") {
            id = platformLibs.plugins.convention.library.compose.get().pluginId
            implementationClass = "plugin.convention.library.LibraryComposePlugin"
        }
        /**======================================================================================**/
        register("DependencyKoinPlugin") {
            id = platformLibs.plugins.dependency.koin.get().pluginId
            implementationClass = "plugin.dependency.DependencyKoinPlugin"
        }
        register("DependencyRoomPlugin") {
            id = platformLibs.plugins.dependency.room.get().pluginId
            implementationClass = "plugin.dependency.DependencyRoomPlugin"
        }
        register("DependencySerialization") {
            id = platformLibs.plugins.dependency.serialization.get().pluginId
            implementationClass = "plugin.dependency.DependencySerialization"
        }
        /**======================================================================================**/
        register("PlatformConverterPlugin") {
            id = platformLibs.plugins.module.platform.converter.get().pluginId
            implementationClass = "plugin.platform.PlatformConverterPlugin"
        }
        register("PlatformDatabasePlugin") {
            id = platformLibs.plugins.module.platform.database.get().pluginId
            implementationClass = "plugin.platform.PlatformDatabasePlugin"
        }
        register("PlatformDataPlugin") {
            id = platformLibs.plugins.module.platform.data.get().pluginId
            implementationClass = "plugin.platform.PlatformDataPlugin"
        }
        register("PlatformDataStorePlugin") {
            id = platformLibs.plugins.module.platform.datastore.get().pluginId
            implementationClass = "plugin.platform.PlatformDataStorePlugin"
        }
        register("PlatformDomainPlugin") {
            id = platformLibs.plugins.module.platform.domain.get().pluginId
            implementationClass = "plugin.platform.PlatformDomainPlugin"
        }
        register("PlatformFeaturePlugin") {
            id = platformLibs.plugins.module.platform.feature.get().pluginId
            implementationClass = "plugin.platform.PlatformFeaturePlugin"
        }

        /** Model **/
        register("PlatformModelBasePlugin") {
            id = platformLibs.plugins.module.platform.model.base.get().pluginId
            implementationClass = "plugin.platform.model.PlatformModelBasePlugin"
        }
        register("PlatformModelCachePlugin") {
            id = platformLibs.plugins.module.platform.model.cache.get().pluginId
            implementationClass = "plugin.platform.model.PlatformModelCachePlugin"
        }
        register("PlatformModelRemotePlugin") {
            id = platformLibs.plugins.module.platform.model.remote.get().pluginId
            implementationClass = "plugin.platform.model.PlatformModelRemotePlugin"
        }
        register("PlatformModelUiPlugin") {
            id = platformLibs.plugins.module.platform.model.ui.get().pluginId
            implementationClass = "plugin.platform.model.PlatformModelUiPlugin"
        }

        register("PlatformNetworkPlugin") {
            id = platformLibs.plugins.module.platform.network.get().pluginId
            implementationClass = "plugin.platform.PlatformNetworkPlugin"
        }
        register("PlatformWorkPlugin") {
            id = platformLibs.plugins.module.platform.work.get().pluginId
            implementationClass = "plugin.platform.PlatformWorkPlugin"
        }
    }
}
