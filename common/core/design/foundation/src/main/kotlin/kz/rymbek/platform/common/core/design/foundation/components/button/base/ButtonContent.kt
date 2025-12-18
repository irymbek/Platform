package kz.rymbek.platform.common.core.design.foundation.components.button.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun ButtonContent(
    text: String,
    color: Color = Color.Unspecified,
) {
    AppText(
        text = text,
        color = color
    )
}