package kz.rymbek.platform.common.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer

/**
 * Create a navigation state that persists config changes and process death.
 */
@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: Set<NavKey>
): NavigationInterface {
    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes,
        serializer = MutableStateSerializer(NavKeySerializer()),
        init = {
            mutableStateOf(startRoute)
        }
    )

    val backStacks = topLevelRoutes.associateWith {
        rememberNavBackStack(it)
    }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks
        )
    }
}

/**
 * State holder for navigation state.
 *
 * @param startRoute - the start route. The user will exit the app through this route.
 * @param topLevelRoute - the current top level route
 * @param backStacks - the back stacks for each top level route
 */
internal class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    override val backStacks: Map<NavKey, NavBackStack<NavKey>>
) : NavigationInterface {
    override var topLevelRoute: NavKey by topLevelRoute

    override val stacksInUse: List<NavKey>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }

    override fun currentBackStack(): NavBackStack<NavKey> = backStacks[topLevelRoute]
        ?: error("No backStack for top level: $topLevelRoute")

    override val currentScreen: NavKey?
        get() = currentBackStack().lastOrNull()

    override fun navigate(key: NavKey) {
        if (key in backStacks.keys) {
            navigateTopLevel(key)
        } else {
            backStacks[topLevelRoute]?.add(key)
        }
    }

    override fun navigateBack() {
        backStacks[topLevelRoute]?.removeLastOrNull()
    }

    private fun navigateTopLevel(key: NavKey) {
        if (backStacks[key]?.lastOrNull() == null) {
            backStacks[key]?.add(key)
        }
        topLevelRoute = key
    }
}

/**
 * Convert NavigationState into NavEntries.
 */
@Composable
fun NavigationInterface.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {

    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            rememberViewModelStoreNavEntryDecorator<NavKey>()
        )
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryProvider
        )
    }

    return stacksInUse
        .flatMap { decoratedEntries[it] ?: emptyList() }
        .toMutableStateList()
}