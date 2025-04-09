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
        println("Convention platform, platform ${files("../../../gradle/libs.versions.toml").asPath}")
        println("Convention platform, project ${files("../../../../gradle/libs.versions.toml").asPath}")

        create("platformLibs") {
            from(files("../../../gradle/libs.versions.toml"))
        }

        create("projectLibs") {
            from(files("../../../../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "convention-platform"
