package kz.rymbek.platform.common.base.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface NavigationInterface {
    var topLevelRoute: NavKey

    val backStacks: Map<NavKey, NavBackStack<NavKey>>

    val stacksInUse: List<NavKey>

    val currentScreen: NavKey?

    fun currentBackStack(): NavBackStack<NavKey>

    fun navigate(key: NavKey)

    fun navigateBack()

    fun navigateTopLevel(target: NavKey)
}