package kz.rymbek.platform.common.base.convention

import com.diffplug.gradle.spotless.FormatExtension
import com.diffplug.gradle.spotless.KotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import kz.rymbek.platform.common.base.convention.extensions.applyPlugin
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureSpotlessForAndroid() {
    applyPlugin(platformLibs.plugins.spotless)
    extensions.configure<SpotlessExtension> {
        configureKotlin(this@configureSpotlessForAndroid)
        configureKts(this@configureSpotlessForAndroid)
        format("xml") {
            target("src/**/*.xml")
            //licenseHeaderFile(rootDir.resolve("spotless/copyright.xml"), "(<[^!?])")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}

internal fun Project.configureSpotlessForJvm() {
    applyPlugin(platformLibs.plugins.spotless)
    extensions.configure<SpotlessExtension> {
        configureKotlin(this@configureSpotlessForJvm)
        configureKts(this@configureSpotlessForJvm)
    }
}

internal fun Project.configureSpotlessForRootProject() {
    applyPlugin(platformLibs.plugins.spotless)
    extensions.configure<SpotlessExtension> {
        kotlin {
            target("build-logic/convention/src/**/*.kt")
            applyKotlinSettings(this@configureSpotlessForRootProject)
        }
        format("kts") {
            target("*.kts", "build-logic/**/*.kts")
            applyKtsSettings(this@configureSpotlessForRootProject)
        }
    }
}

private fun SpotlessExtension.configureKotlin(project: Project) {
    kotlin {
        target("src/**/*.kt")
        targetExclude(
            "**/build/**/*.kt",
            "**/src/test/**/*.kt",
            "**/src/androidTest/**/*.kt"
        )

        applyKotlinSettings(project)
    }
}

private fun SpotlessExtension.configureKts(project: Project) {
    format("kts") {
        target("src/**/*.kts", "*.kts")
        applyKtsSettings(project)
    }
}

private fun KotlinExtension.applyKotlinSettings(project: Project) {
    val ktlintVersion = project.platformLibs.versions.ktlint.get()

    ktlint(ktlintVersion).editorConfigOverride(
        mapOf("android" to "true")
    )
    //licenseHeaderFile(project.rootDir.resolve("spotless/copyright.kt"))
    trimTrailingWhitespace()
    endWithNewline()
}

private fun FormatExtension.applyKtsSettings(project: Project) {
    /*licenseHeaderFile(
        project.rootDir.resolve("spotless/copyright.kts"),
        "(^(?![\\/ ]\\*).*$)" // Регулярка, чтобы вставить после возможного заголовка
    )*/
    trimTrailingWhitespace()
    endWithNewline()
}