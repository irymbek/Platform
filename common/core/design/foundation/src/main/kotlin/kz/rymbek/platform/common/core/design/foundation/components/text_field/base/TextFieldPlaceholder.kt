package kz.rymbek.platform.common.core.design.foundation.components.text_field.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun TextFieldPlaceholder(
    placeholder: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    AppText(
        text = placeholder,
        style = style
    )
}