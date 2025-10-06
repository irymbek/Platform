package kz.rymbek.platform.common.core.design.foundation.components.list_item.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun ListItemSupportingContent(
    text: String?,
) {
    AppText(
        text = text ?: "-",
        style = MaterialTheme.typography.labelMedium,
    )
}