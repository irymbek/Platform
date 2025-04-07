pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            print("rootDir convention-platform: ")
            println(files("../../../gradle/libs.versions.toml").asPath)
            from(files("../../../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "convention-platform"
