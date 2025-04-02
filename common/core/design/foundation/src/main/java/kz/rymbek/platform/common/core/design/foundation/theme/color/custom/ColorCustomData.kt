package kz.rymbek.platform.common.core.design.foundation.theme.color.custom

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorCustomData(
    val success: Color = Color.Unspecified,
    val onSuccess: Color = Color.Unspecified,
    val successContainer: Color = Color.Unspecified,
    val onSuccessContainer: Color = Color.Unspecified,

    val neutral: Color = Color.Unspecified,
    val onNeutral: Color = Color.Unspecified,
    val neutralContainer: Color = Color.Unspecified,
    val onNeutralContainer: Color = Color.Unspecified,
)

val LocalColorsPaletteCustom  = staticCompositionLocalOf { ColorCustomData() }

val MaterialTheme.colorSchemeCustom: ColorCustomData
    @Composable
    @ReadOnlyComposable
    get() = LocalColorsPaletteCustom.current
