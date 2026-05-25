package kz.rymbek.platform.common.core.design.foundation.components.badge

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppBadge(
    modifier: Modifier = Modifier,
    containerColor: Color = BadgeDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable (RowScope.() -> Unit)? = null
) {
    Badge(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        content = content,
    )
}
