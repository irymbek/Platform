package kz.rymbek.platform.common.base.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import kz.rymbek.platform.common.base.convention.extensions.debugImplementation
import kz.rymbek.platform.common.base.convention.extensions.implementation
import kz.rymbek.platform.common.base.convention.extensions.platformLibs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

fun Project.configureAndroidCompose(
    commonExtension: CommonExtension,
) {
    when (commonExtension) {
        is ApplicationExtension -> commonExtension.buildFeatures.compose = true
        is LibraryExtension -> commonExtension.buildFeatures.compose = true
    }
    dependencies {
        implementation(platform(platformLibs.androidx.compose.bom))
        implementation(platformLibs.androidx.compose.ui.tooling.preview)
        debugImplementation(platformLibs.androidx.compose.ui.tooling)
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }

        fun Provider<*>.relativeToRootProject(dirName: String) = map {
            layout.buildDirectory.dir("compose-reports/$dirName").get()
        }

        val enableMetrics = providers.gradleProperty("enableComposeCompilerMetrics").onlyIfTrue()
        val enableReports = providers.gradleProperty("enableComposeCompilerReports").onlyIfTrue()

        metricsDestination.set(enableMetrics.relativeToRootProject("metrics"))
        reportsDestination.set(enableReports.relativeToRootProject("reports"))
    }
}
