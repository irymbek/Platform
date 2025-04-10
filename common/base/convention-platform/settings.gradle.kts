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
        val platform = files("../../../gradle/libs.versions.toml")

        val project = if (rootDir.toString().contains("Platform")) {
            files("../../../libs.versions.toml")
        } else {
            files("../../../../gradle/libs.versions.toml")
        }

        println("Convention project, project ${project.asPath}")

        create("platformLibs") {
            from(platform)
        }

        create("projectLibs") {
            from(project)
        }
    }
}

rootProject.name = "convention-platform"
