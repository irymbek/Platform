package kz.rymbek.platform.common.core.design.foundation.components.badge

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppBadgedBox(
    badge: @Composable (BoxScope.() -> Unit),
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    BadgedBox(
        badge = badge,
        modifier = modifier,
        content = content,
    )
}
