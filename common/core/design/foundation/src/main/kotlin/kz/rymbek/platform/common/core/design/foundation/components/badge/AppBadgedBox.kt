package kz.rymbek.platform.common.core.design.foundation.components.badge

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppBadgedBox(
    modifier: Modifier = Modifier,
    text: String? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    BadgedBox(
        badge = {
            text?.let {
                AppBadge(
                    text = it,
                )
            }
        },
        content = content,
        modifier = modifier,
    )
}