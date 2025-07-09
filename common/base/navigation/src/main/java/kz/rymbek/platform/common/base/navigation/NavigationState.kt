package kz.rymbek.platform.common.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNavigationState(): NavigationState {
    val navHostController: NavHostController = rememberNavController()
    return remember(navHostController) {
        NavigationState(
            navHostController = navHostController,
        )
    }
}

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
        route: Any,
    ): Boolean {
        return destination?.hierarchy?.any {
            it.hasRoute(route::class)
        } == true
    }
}
