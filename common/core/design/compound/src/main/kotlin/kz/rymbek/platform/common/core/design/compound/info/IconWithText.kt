package kz.rymbek.platform.common.core.design.compound.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import kz.rymbek.platform.common.core.design.foundation.components.container.AppRow
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

@Composable
fun IconWithContent(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconSize: Dp = PlatformIconSize.xs,
    spacing: Dp = PlatformPaddings.element,
    content: @Composable () -> Unit
) {
    AppRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        content = {
            AppIcon(
                imageVector = icon,
                modifier = Modifier.size(iconSize),
            )
            content()
        }
    )
}