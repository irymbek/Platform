package kz.rymbek.platform.common.core.design.foundation.components.button.base

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun ButtonContent(
    text: String,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current
) {
    AppText(
        text = text,
        color = color,
        style = style,
    )
}