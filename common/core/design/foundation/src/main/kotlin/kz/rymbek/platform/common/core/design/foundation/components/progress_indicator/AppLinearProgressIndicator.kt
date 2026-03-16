package kz.rymbek.platform.common.core.design.foundation.components.progress_indicator

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

@Composable
fun AppLinearProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.linearColor,
    trackColor: Color = ProgressIndicatorDefaults.linearTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    gapSize: Dp = ProgressIndicatorDefaults.LinearIndicatorTrackGapSize,
) {
    LinearProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor,
        strokeCap = strokeCap,
        gapSize = gapSize,
    )
}

@Composable
fun AppLinearProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.linearColor,
    trackColor: Color = ProgressIndicatorDefaults.linearTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    gapSize: Dp = ProgressIndicatorDefaults.LinearIndicatorTrackGapSize,
    drawStopIndicator: DrawScope.() -> Unit = {
        ProgressIndicatorDefaults.drawStopIndicator(
            drawScope = this,
            stopSize = ProgressIndicatorDefaults.LinearTrackStopIndicatorSize,
            color = color,
            strokeCap = strokeCap,
        )
    },
) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = color,
        trackColor = trackColor,
        strokeCap = strokeCap,
        gapSize = gapSize,
        drawStopIndicator = drawStopIndicator,
    )
}