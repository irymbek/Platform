package kz.rymbek.platform.common.core.design.foundation.components.card

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun AppElevatedCard(
    modifier: Modifier = Modifier,
    shape: Shape = AppCardDefaults.elevatedShape,
    colors: CardColors = AppCardDefaults.cardColors(),
    elevation: CardElevation = AppCardDefaults.elevatedCardElevation(),
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation,
        content = content,
    )
}

@Composable
fun AppElevatedCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = AppCardDefaults.elevatedShape,
    colors: CardColors = AppCardDefaults.cardColors(),
    elevation: CardElevation = AppCardDefaults.elevatedCardElevation(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        interactionSource = interactionSource,
        content = content,
    )
}