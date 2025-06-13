package kz.rymbek.platform.common.core.design.foundation.components.background

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.container.AppBox
import kz.rymbek.platform.common.core.design.foundation.theme.AppTheme
import kz.rymbek.platform.common.core.design.foundation.theme.background.GradientColors
import kz.rymbek.platform.common.core.design.foundation.theme.background.LocalBackgroundTheme
import kz.rymbek.platform.common.core.design.foundation.theme.background.LocalGradientColors
import kotlin.math.tan

private fun Color.orTransparent() = if (this == Color.Unspecified) Color.Transparent else this

@Composable
fun AppBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val theme = LocalBackgroundTheme.current
    Surface(
        color = theme.color.orTransparent(),
        tonalElevation = theme.tonalElevation.takeIf { it != Dp.Unspecified } ?: 0.dp,
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
            content()
        }
    }
}

@Composable
fun AppGradientBackground(
    modifier: Modifier = Modifier,
    gradientColors: GradientColors = LocalGradientColors.current,
    content: @Composable BoxScope.() -> Unit,
) {
    val gradientContainer = gradientColors.container.orTransparent()
    val topColor = gradientColors.top.orTransparent()
    val bottomColor = gradientColors.bottom.orTransparent()
    val gradientAngleRad = Math.toRadians(11.06).toFloat()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = gradientContainer,
    ) {
        AppBox(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val offset = size.height * tan(gradientAngleRad)

                    val start = Offset(size.width / 2 + offset / 2, 0f)
                    val end = Offset(size.width / 2 - offset / 2, size.height)

                    val topGradient = Brush.linearGradient(
                        0f to topColor,
                        0.724f to Color.Transparent,
                        start = start,
                        end = end,
                    )
                    val bottomGradient = Brush.linearGradient(
                        0.2552f to Color.Transparent,
                        1f to bottomColor,
                        start = start,
                        end = end,
                    )

                    onDrawBehind {
                        drawRect(topGradient)
                        drawRect(bottomGradient)
                    }
                },
            content = content,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
annotation class ThemePreviews

@ThemePreviews
@Composable
fun BackgroundDefault() {
    AppTheme {
        AppBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun GradientBackgroundDefault() {
    AppTheme {
        AppGradientBackground(Modifier.size(100.dp), content = {})
    }
}

