package kz.rymbek.platform.common.core.design.foundation.components.drop_down_menu.exposed

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.image.AppAsyncImage
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun AppDropdownMenuItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIconUri: Any? = null,
    enabled: Boolean = true,
    colors: MenuItemColors = MenuDefaults.itemColors(),
    contentPadding: PaddingValues = ExposedDropdownMenuDefaults.ItemContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    DropdownMenuItem(
        modifier = modifier,
        text = {
            AppText(
                modifier = Modifier,
                text = text,
            )
        },
        onClick = onClick,
        leadingIcon = leadingIcon,
        trailingIcon = {
            if(trailingIconUri != null) {
                AppAsyncImage(
                    modifier = Modifier
                        .size(100.dp),
                    uri = trailingIconUri,
                )
            }
        },
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}