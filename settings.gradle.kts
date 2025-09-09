pluginManagement {
    includeBuild("common/base/convention/convention-platform") {
        name = "platform"
    }
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("platformLibs") {
            from(files("gradle/platformLibs.versions.toml"))
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Platform"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
/** ================================================================================================
 * COMMON
 * ============================================================================================= **/

/** Common: Base **/
include(":common:base:app")
include(":common:base:converter")
include(":common:base:data")
include(":common:base:database")
include(":common:base:datastore")
include(":common:base:feature")
include(":common:base:model")
include(":common:base:network")
include(":common:base:work")

/** Common: Business **/
include(":common:business:converter")
include(":common:business:model")

/** Common: Core **/
include(":common:core:architecture")
include(":common:core:design:compound")
include(":common:core:design:foundation")
include(":common:core:date")
/**==============================================================================================**/