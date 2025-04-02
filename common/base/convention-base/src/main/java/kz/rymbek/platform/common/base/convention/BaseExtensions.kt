package kz.rymbek.platform.common.base.convention

import libs
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

fun Project.applyPlugin(plugin: Provider<PluginDependency>) {
    pluginManager.apply(plugin.get().pluginId)
}

fun Project.applyPlugin(plugin: LibrariesForLibs.KotlinPluginAccessors) {
    pluginManager.apply(plugin.asProvider().get().pluginId)
}

fun Project.applyPlugin(plugin: LibrariesForLibs. ConventionLibraryPluginAccessors) {
    pluginManager.apply(plugin.asProvider().get().pluginId)
}

inline fun <reified T : Plugin<Project>> Project.applyPlugin() {
    pluginManager.apply(T::class.java)
}

fun DependencyHandlerScope.implementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.implementation(
    path: String,
) {
    add("implementation", project(path))
}

fun DependencyHandlerScope.ksp(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("ksp", dependency)
}

fun DependencyHandlerScope.debugImplementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("debugImplementation", dependency)
}

fun DependencyHandlerScope.debugImplementation(
    dependency: LibrariesForLibs.AndroidxComposeUiToolingLibraryAccessors
) {
    add("debugImplementation", dependency)
}

val Project.projectJavaVersion: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.java.get().toInt())

val Project.projectJvmTarget: JvmTarget
    get() = JvmTarget.fromTarget(libs.versions.java.get())