package kz.rymbek.platform.common.base.navigation

import android.util.Log
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import org.koin.core.annotation.Single

@Single(
    binds = [BaseNavigatorInterface::class]
)
class BaseNavigator: BaseNavigatorInterface {
    private lateinit var navHostController: NavHostController

    override fun setNavController(navHostController: NavHostController) {
        this.navHostController = navHostController
        navHostController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("BaseNavigator", "navigate, route: ${destination.route}")
        }
    }

    override fun navigateBack() {
        navHostController.navigateUp()
    }

    override fun <T: Any> navigate(
        route: T,
        navOptions: NavOptions?,
    ) {
        navHostController.navigate(
            route = route,
            navOptions = navOptions,
        )
    }

    override fun <T: Any> navigateToTopLevelDestination(destination: T) {
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