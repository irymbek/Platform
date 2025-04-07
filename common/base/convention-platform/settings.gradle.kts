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
        create(defaultLibrariesExtensionName.get()) {
            println("rootProject: $rootProject")
            println("rootDir: ${rootDir}")
            println("rootDir.parent: ${rootDir.parent}")
            from(files("../../../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "convention-base"
