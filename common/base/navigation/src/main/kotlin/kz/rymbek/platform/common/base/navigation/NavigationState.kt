package kz.rymbek.platform.common.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlin.reflect.KClass

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember(navHostController) {
        NavigationState(
            navHostController = navHostController,
        )
    }
}

@Stable
class NavigationState(
    private val navHostController: NavHostController,
) {
    val currentNavHostController: NavHostController
        get() = navHostController

    val currentDestination: NavDestination?
        @Composable get() = navHostController
            .currentBackStackEntryAsState().value?.destination

    fun isTopLevelDestinationInHierarchy(
        destination: NavDestination?,
        route: KClass<*>,
    ): Boolean {
        return destination?.hierarchy?.any {
            it.hasRoute(route)
        } == true
    }

    fun navigateBack() {
        navHostController.navigateUp()
    }

    fun <T : Any> navigateToTopLevelDestination(destination: T) {
        val topLevelNavOptions = navOptions {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        navHostController.navigate(
            route = destination,
            navOptions = topLevelNavOptions,
        )
    }
}
