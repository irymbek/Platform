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
            implementationClass = "plugin.module.base.ModuleDatabaseBasePlugin"
        }
        register("moduleBaseData") {
            id = libs.plugins.module.base.data.get().pluginId
            implementationClass = "plugin.module.base.ModuleDataBasePlugin"
        }
        register("moduleBaseDatastore") {
            id = libs.plugins.module.base.datastore.get().pluginId
            implementationClass = "plugin.module.base.ModuleDataStoreBasePlugin"
        }
        register("moduleBaseDomain") {
            id = libs.plugins.module.base.domain.get().pluginId
            implementationClass = "plugin.module.base.ModuleDomainBasePlugin"
        }
        register("moduleBaseFeature") {
            id = libs.plugins.module.base.feature.get().pluginId
            implementationClass = "plugin.module.base.ModuleFeatureBasePlugin"
        }
        register("moduleBaseModel") {
            id = libs.plugins.module.base.model.get().pluginId
            implementationClass = "plugin.module.base.ModuleModelBasePlugin"
        }
        register("moduleBaseNetwork") {
            id = libs.plugins.module.base.network.get().pluginId
            implementationClass = "plugin.module.base.ModuleNetworkBasePlugin"
        }
        register("moduleBaseWork") {
            id = libs.plugins.module.base.work.get().pluginId
            implementationClass = "plugin.module.base.ModuleWorkBasePlugin"
        }
        /**======================================================================================**/
        register("moduleDataCommon") {
            id = libs.plugins.module.project.common.data.get().pluginId
            implementationClass = "plugin.module.project.common.ModuleDataCommonPlugin"
        }
        register("moduleDatabaseCommon") {
            id = libs.plugins.module.project.common.database.get().pluginId
            implementationClass = "plugin.module.project.common.ModuleDatabaseCommonPlugin"
        }
        register("moduleDataStoreCommon") {
            id = libs.plugins.module.project.common.datastore.get().pluginId
            implementationClass = "plugin.module.project.common.ModuleDataStoreCommonPlugin"
        }
        register("moduleDomainCommon") {
            id = libs.plugins.module.project.common.domain.get().pluginId
            implementationClass = "plugin.module.project.common.ModuleDomainCommonPlugin"
        }
        register("moduleFeatureCommon") {
            id = libs.plugins.module.project.common.feature.get().pluginId
            implementationClass = "plugin.module.project.common.ModuleFeatureCommonPlugin"
        }
        register("moduleNetworkCommon") {
            id = libs.plugins.module.project.common.network.get().pluginId
            implementationClass = "plugin.module.project.common.ModuleNetworkCommonPlugin"
        }
        /**======================================================================================**/
        register("moduleDataEngineer") {
            id = libs.plugins.module.project.engineer.data.get().pluginId
            implementationClass = "plugin.module.project.engineer.ModuleDataEngineerPlugin"
        }
        register("moduleDatabaseEngineer") {
            id = libs.plugins.module.project.engineer.database.get().pluginId
            implementationClass = "plugin.module.project.engineer.ModuleDatabaseEngineerPlugin"
        }
        register("moduleDataStoreEngineer") {
            id = libs.plugins.module.project.engineer.datastore.get().pluginId
            implementationClass = "plugin.module.project.engineer.ModuleDataStoreEngineerPlugin"
        }
        register("moduleDomainEngineer") {
            id = libs.plugins.module.project.engineer.domain.get().pluginId
            implementationClass = "plugin.module.project.engineer.ModuleDomainEngineerPlugin"
        }
        register("moduleFeatureEngineer") {
            id = libs.plugins.module.project.engineer.feature.get().pluginId
            implementationClass = "plugin.module.project.engineer.ModuleFeatureEngineerPlugin"
        }
        register("moduleNetworkEngineer") {
            id = libs.plugins.module.project.engineer.network.get().pluginId
            implementationClass = "plugin.module.project.engineer.ModuleNetworkEngineerPlugin"
        }
        /**======================================================================================**/
        register("moduleDataLaboratory") {
            id = libs.plugins.module.project.laboratory.data.get().pluginId
            implementationClass = "plugin.module.project.laboratory.ModuleDataLaboratoryPlugin"
        }
        register("moduleDatabaseLaboratory") {
            id = libs.plugins.module.project.laboratory.database.get().pluginId
            implementationClass = "plugin.module.project.laboratory.ModuleDatabaseLaboratoryPlugin"
        }
        register("moduleDataStoreLaboratory") {
            id = libs.plugins.module.project.laboratory.datastore.get().pluginId
            implementationClass = "plugin.module.project.laboratory.ModuleDataStoreLaboratoryPlugin"
        }
        register("moduleDomainLaboratory") {
            id = libs.plugins.module.project.laboratory.domain.get().pluginId
            implementationClass = "plugin.module.project.laboratory.ModuleDomainLaboratoryPlugin"
        }
        register("moduleFeatureLaboratory") {
            id = libs.plugins.module.project.laboratory.feature.get().pluginId
            implementationClass = "plugin.module.project.laboratory.ModuleFeatureLaboratoryPlugin"
        }
        register("moduleNetworkLaboratory") {
            id = libs.plugins.module.project.laboratory.network.get().pluginId
            implementationClass = "plugin.module.project.laboratory.ModuleNetworkLaboratoryPlugin"
        }
    }
}
