pluginManagement {
    includeBuild("base-build-logic")
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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Platform"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":common:base:app")
include(":common:base:converter")
include(":common:base:data")
include(":common:base:database")
include(":common:base:datastore")
include(":common:base:feature")
include(":common:base:model")
include(":common:base:network")
include(":common:base:work")
include(":common:base:test")
/**==============================================================================================**/
include(":common:business:converter")
/**==============================================================================================**/
include(":common:core:architecture")
include(":common:core:design:compound")
include(":common:core:design:foundation")
