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

        val project = if (settingsDir.absolutePath.contains("\\platform\\")) {
            files("../../../../gradle/libs.versions.toml")
        } else {
            files("../../../libs.versions.toml")
        }

        println("Convention platform, platform ${platform.asPath}")
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
