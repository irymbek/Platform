package kz.rymbek.platform.common.core.design.foundation.components.text_field.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun OutlinedTextFieldLabel(
    label: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    style: TextStyle = MaterialTheme.typography.labelMedium
) {
    AppText(
        text = label,
        modifier = modifier,
        color = color,
        style = style,
    )
}