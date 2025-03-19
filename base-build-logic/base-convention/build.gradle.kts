import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "kz.uso.krl.common.convention.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("conventionJvm") {
            id = libs.plugins.convention.jvm.get().pluginId
            implementationClass = "plugin.convention.jvm.JvmPlugin"
        }
        /*========================================================================================*/
        register("conventionApplication") {
            id = libs.plugins.convention.application.asProvider().get().pluginId
            implementationClass = "plugin.convention.application.ApplicationPlugin"
        }
        register("conventionApplicationCompose") {
            id = libs.plugins.convention.application.compose.get().pluginId
            implementationClass = "plugin.convention.application.ApplicationComposePlugin"
        }
        /*========================================================================================*/
        register("conventionLibrary") {
            id = libs.plugins.convention.library.asProvider().get().pluginId
            implementationClass = "plugin.convention.library.LibraryPlugin"
        }
        register("conventionLibraryCompose") {
            id = libs.plugins.convention.library.compose.get().pluginId
            implementationClass = "plugin.convention.library.LibraryComposePlugin"
        }
        /**======================================================================================**/
        register("dependencyKoin") {
            id = libs.plugins.dependency.koin.get().pluginId
            implementationClass = "plugin.dependency.DependencyKoinPlugin"
        }
        register("dependencyRoom") {
            id = libs.plugins.dependency.room.get().pluginId
            implementationClass = "plugin.dependency.DependencyRoomPlugin"
        }
        register("dependencySerialization") {
            id = libs.plugins.dependency.serialization.get().pluginId
            implementationClass = "plugin.dependency.DependencySerialization"
        }
        /**======================================================================================**/
        register("moduleBaseDatabase") {
            id = libs.plugins.module.base.database.get().pluginId
            implementationClass = "plugin.module.ModuleDatabaseBasePlugin"
        }
        register("moduleBaseData") {
            id = libs.plugins.module.base.data.get().pluginId
            implementationClass = "plugin.module.ModuleDataBasePlugin"
        }
        register("moduleBaseDatastore") {
            id = libs.plugins.module.base.datastore.get().pluginId
            implementationClass = "plugin.module.ModuleDataStoreBasePlugin"
        }
        register("moduleBaseDomain") {
            id = libs.plugins.module.base.domain.get().pluginId
            implementationClass = "plugin.module.ModuleDomainBasePlugin"
        }
        register("moduleBaseFeature") {
            id = libs.plugins.module.base.feature.get().pluginId
            implementationClass = "plugin.module.ModuleFeatureBasePlugin"
        }
        register("moduleBaseModel") {
            id = libs.plugins.module.base.model.get().pluginId
            implementationClass = "plugin.module.ModuleModelBasePlugin"
        }
        register("moduleBaseNetwork") {
            id = libs.plugins.module.base.network.get().pluginId
            implementationClass = "plugin.module.ModuleNetworkBasePlugin"
        }
        register("moduleBaseWork") {
            id = libs.plugins.module.base.work.get().pluginId
            implementationClass = "plugin.module.ModuleWorkBasePlugin"
        }
    }
}
