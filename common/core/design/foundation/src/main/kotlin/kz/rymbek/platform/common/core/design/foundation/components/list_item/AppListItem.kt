package kz.rymbek.platform.common.core.design.foundation.components.list_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions

@Composable
fun AppListItem(
    headlineText: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingText: String? = null,
    supportingTextColor: Color = Color.Unspecified,
    leadingContent: @Composable() (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    colors: ListItemColors = ListItemDefaults.colors(),
    tonalElevation: Dp = ListItemDefaults.Elevation,
    shadowElevation: Dp = ListItemDefaults.Elevation,
) {
    ListItem(
        headlineContent = {
            AppText(
                text = headlineText,
                maxLines = 3,
                style = MaterialTheme.typography.titleSmall,
            )
        },
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        overlineContent = overlineContent,
        supportingContent = supportingText?.let { text ->
            {
                AppText(
                    modifier = Modifier
                        .padding(top = Dimensions.elementPaddingDp),
                    text = text,
                    color = supportingTextColor,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        },
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        colors = colors,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
    )
}