package kz.rymbek.platform.common.base.convention.extensions

import org.gradle.accessors.dm.LibrariesForProjectLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

val Project.projectLibs: LibrariesForProjectLibs
    get() = the<LibrariesForProjectLibs>()