package kz.rymbek.platform.common.base.navigation.core

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions

interface BaseNavigatorInterface {
    fun setNavController(navHostController: NavHostController)
    fun navigateBack()
    fun <T : Any> navigate(route: T, navOptions: NavOptions? = null)
    fun <T : Any> navigateToTopLevelDestination(destination: T)
}