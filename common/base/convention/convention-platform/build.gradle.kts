//import kz.rymbek.platform.common.base.convention.extensions.conventionPlugin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "kz.rymbek.platform.common.base.convention"

private val projectJavaVersion: JavaVersion =
    JavaVersion.toVersion(platformLibs.versions.java.get())
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
    compileOnly(platformLibs.spotless.gradlePlugin)
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
        /** Build ===============================================================================**/
        register("JvmPlugin") {
            id = platformLibs.plugins.build.jvm.get().pluginId
            implementationClass = "plugin.build.jvm.JvmPlugin"
        }
        /*========================================================================================*/
        register("ApplicationPlugin") {
            id = platformLibs.plugins.build.application.asProvider().get().pluginId
            implementationClass = "plugin.build.application.ApplicationPlugin"
        }
        register("ApplicationComposePlugin") {
            id = platformLibs.plugins.build.application.compose.get().pluginId
            implementationClass = "plugin.build.application.ApplicationComposePlugin"
        }
        /*========================================================================================*/
        register("LibraryPlugin") {
            id = platformLibs.plugins.build.library.asProvider().get().pluginId
            implementationClass = "plugin.build.library.LibraryPlugin"
        }
        register("LibraryComposePlugin") {
            id = platformLibs.plugins.build.library.compose.get().pluginId
            implementationClass = "plugin.build.library.LibraryComposePlugin"
        }
        /** Dependency ==========================================================================**/
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
        /** Layer ===============================================================================**/
        register("PlatformDataApiPlugin") {
            id = platformLibs.plugins.layer.platform.data.api.get().pluginId
            implementationClass = "plugin.layer.data.PlatformDataApiPlugin"
        }
        register("PlatformDataImplPlugin") {
            id = platformLibs.plugins.layer.platform.data.impl.get().pluginId
            implementationClass = "plugin.layer.data.PlatformDataImplPlugin"
        }
        /*========================================================================================*/
        register("PlatformDomainApiPlugin") {
            id = platformLibs.plugins.layer.platform.domain.api.get().pluginId
            implementationClass = "plugin.layer.domain.PlatformDomainApiPlugin"
        }
        register("PlatformDomainImplPlugin") {
            id = platformLibs.plugins.layer.platform.domain.impl.get().pluginId
            implementationClass = "plugin.layer.domain.PlatformDomainImplPlugin"
        }
        /*========================================================================================*/
        register("PlatformFeatureApiPlugin") {
            id = platformLibs.plugins.layer.platform.feature.api.get().pluginId
            implementationClass = "plugin.layer.feature.PlatformFeatureApiPlugin"
        }
        register("PlatformFeatureImplPlugin") {
            id = platformLibs.plugins.layer.platform.feature.impl.get().pluginId
            implementationClass = "plugin.layer.feature.PlatformFeatureImplPlugin"
        }
        /*========================================================================================*/
        register("PlatformModelBasePlugin") {
            id = platformLibs.plugins.layer.platform.model.base.get().pluginId
            implementationClass = "plugin.layer.model.PlatformModelBasePlugin"
        }
        register("PlatformModelCachePlugin") {
            id = platformLibs.plugins.layer.platform.model.cache.get().pluginId
            implementationClass = "plugin.layer.model.PlatformModelCachePlugin"
        }
        register("PlatformModelRemotePlugin") {
            id = platformLibs.plugins.layer.platform.model.remote.get().pluginId
            implementationClass = "plugin.layer.model.PlatformModelRemotePlugin"
        }
        register("PlatformModelUiPlugin") {
            id = platformLibs.plugins.layer.platform.model.ui.get().pluginId
            implementationClass = "plugin.layer.model.PlatformModelUiPlugin"
        }
        /*========================================================================================*/
        register("PlatformConverterPlugin") {
            id = platformLibs.plugins.layer.platform.converter.get().pluginId
            implementationClass = "plugin.layer.PlatformConverterPlugin"
        }
        register("PlatformDatabasePlugin") {
            id = platformLibs.plugins.layer.platform.database.get().pluginId
            implementationClass = "plugin.layer.PlatformDatabasePlugin"
        }
        register("PlatformDataStorePlugin") {
            id = platformLibs.plugins.layer.platform.datastore.get().pluginId
            implementationClass = "plugin.layer.PlatformDataStorePlugin"
        }
        register("PlatformNetworkPlugin") {
            id = platformLibs.plugins.layer.platform.network.get().pluginId
            implementationClass = "plugin.layer.PlatformNetworkPlugin"
        }
        register("PlatformWorkPlugin") {
            id = platformLibs.plugins.layer.platform.work.get().pluginId
            implementationClass = "plugin.layer.PlatformWorkPlugin"
        }
    }
}
