package kz.rymbek.platform.common.core.design.foundation.components.text_field.outlined

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun AppOutlinedTextFieldValidation(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    errorMessage: String?,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    AppOutlinedTextField(
        modifier = modifier,
        value = value,
        label = label,
        onValueChange = onValueChange,
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) {
                AppText(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        keyboardType = keyboardType,
    )
}