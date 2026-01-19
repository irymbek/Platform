buildscript {
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
}

plugins {
    alias(platformLibs.plugins.android.application) apply false
    alias(platformLibs.plugins.android.library) apply false
    alias(platformLibs.plugins.compose) apply false
    alias(platformLibs.plugins.kotlin.jvm) apply false
    alias(platformLibs.plugins.kotlin.serialization) apply false
    alias(platformLibs.plugins.ksp) apply false
    alias(platformLibs.plugins.room) apply false
}