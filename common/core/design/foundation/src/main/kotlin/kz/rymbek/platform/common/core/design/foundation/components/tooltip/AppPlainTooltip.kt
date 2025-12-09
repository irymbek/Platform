package kz.rymbek.platform.common.core.design.foundation.components.tooltip

import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TooltipScope.AppPlainTooltip(
    modifier: Modifier = Modifier,
    caretShape: (Shape)? = null,
    maxWidth: Dp = TooltipDefaults.plainTooltipMaxWidth,
    shape: Shape = TooltipDefaults.plainTooltipContainerShape,
    contentColor: Color = TooltipDefaults.plainTooltipContentColor,
    containerColor: Color = TooltipDefaults.plainTooltipContainerColor,
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    PlainTooltip(
        modifier = modifier,
        caretShape = caretShape,
        maxWidth = maxWidth,
        shape = shape,
        contentColor = contentColor,
        containerColor = containerColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        content = content,
    )
}