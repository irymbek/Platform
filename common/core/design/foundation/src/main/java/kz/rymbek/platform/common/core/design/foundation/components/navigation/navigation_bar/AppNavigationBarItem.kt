package kz.rymbek.platform.common.core.design.foundation.components.navigation.navigation_bar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import kz.rymbek.platform.common.core.design.foundation.components.badge.AppBadgedBox
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun RowScope.AppNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    image: ImageVector,
    selectedImage: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    alwaysShowLabel: Boolean = true,
    badgeText: String? = null,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
    interactionSource: MutableInteractionSource? = null
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            AppBadgedBox(
                text = badgeText,
                content = {
                    AppIcon(
                        imageVector = if (selected) selectedImage else image,
                    )
                }
            )
        },
        modifier = modifier,
        enabled = enabled,
        label = {
            AppText(
                text = label,
                style = MaterialTheme.typography.labelSmall
            )
        },
        alwaysShowLabel = alwaysShowLabel,
        colors = colors,
        interactionSource = interactionSource,
    )
}