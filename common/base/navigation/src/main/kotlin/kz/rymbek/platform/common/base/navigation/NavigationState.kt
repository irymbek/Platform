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
): NavigationState {
    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes,
        serializer = MutableStateSerializer(NavKeySerializer()),
        init = {
            mutableStateOf(startRoute)
        }
    )

    val backStacks = topLevelRoutes.associateWith { key -> rememberNavBackStack(key) }

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
class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoute: NavKey by topLevelRoute

    val stacksInUse: List<NavKey>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }

    /** Возвращает текущий активный backStack (для текущего topLevelRoute). */
    fun currentBackStack(): NavBackStack<NavKey> = backStacks[topLevelRoute]
        ?: error("No backStack for top level: $topLevelRoute")

    val currentScreen: NavKey?
        get() = currentBackStack().lastOrNull()

    /** Навигация: добавляет элемент в текущий backStack. */
    fun navigate(key: NavKey) {
        backStacks[topLevelRoute]?.add(key)
    }

    /** Навигация вверх: удаляет последний элемент в текущем backStack (если есть). */
    fun navigateUp() {
        backStacks[topLevelRoute]?.removeLastOrNull()
    }

    /**
     * Переключает текущий top-level. При переключении не меняем содержимое стека — просто меняем активный backStack.
     * Если требуется при переключении очистить стек — вызывай clearTarget = true.
     */

    fun navigateTopLevel(target: NavKey, clearTarget: Boolean = false) {
        if (!backStacks.containsKey(target)) error("Unknown top level route: $target")

        if (clearTarget) {
            backStacks[target]?.clear()
            backStacks[target]?.add(target)
        } else {
            // Если стек целевого топ-левела пуст — добавим туда сам ключ,
            // чтобы currentScreen не стал null при переключении.
            if (backStacks[target]?.lastOrNull() == null) {
                backStacks[target]?.add(target)
            }
        }

        // Наконец — переключаем активный topLevelRoute
        topLevelRoute = target
    }
}

/**
 * Convert NavigationState into NavEntries.
 */
@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {

    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
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