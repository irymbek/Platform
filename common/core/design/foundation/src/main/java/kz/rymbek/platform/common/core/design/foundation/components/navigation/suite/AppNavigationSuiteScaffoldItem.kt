package kz.rymbek.platform.common.core.design.foundation.components.navigation.suite

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon

fun NavigationSuiteScope.appNavigationSuiteScaffoldItem(
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    badge: (@Composable () -> Unit)? = null,
    colors: NavigationSuiteItemColors? = null,
    interactionSource: MutableInteractionSource? = null
) {
    item(
        selected = selected,
        onClick = onClick,
        icon = {
            AppIcon(
                imageVector = if (selected) selectedIcon else unselectedIcon,
            )
        },
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        badge = badge,
        colors = colors,
        interactionSource = interactionSource,
    )
}