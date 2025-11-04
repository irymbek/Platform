package kz.rymbek.platform.common.core.design.foundation.components.tooltop

import androidx.compose.material3.RichTooltip
import androidx.compose.material3.RichTooltipColors
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun TooltipScope.AppRichTooltip(
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    caretShape: (Shape)? = null,
    maxWidth: Dp = TooltipDefaults.richTooltipMaxWidth,
    shape: Shape = TooltipDefaults.richTooltipContainerShape,
    colors: RichTooltipColors = TooltipDefaults.richTooltipColors(),
    text: @Composable () -> Unit,
) {
    RichTooltip(
        modifier = modifier,
        title = title,
        action = action,
        caretShape = caretShape,
        maxWidth = maxWidth,
        shape = shape,
        colors = colors,
        text = text,
    )
}