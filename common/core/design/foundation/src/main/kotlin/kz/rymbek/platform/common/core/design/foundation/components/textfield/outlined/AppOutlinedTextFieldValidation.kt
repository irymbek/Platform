package kz.rymbek.platform.common.core.design.foundation.components.textfield.outlined

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun AppOutlinedTextFieldValidation(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    AppOutlinedTextField(
        modifier = modifier,
        value = value,
        label = label,
        onValueChange = onValueChange,
        enabled = enabled,
        isError = errorMessage != null,
        supportingText = errorMessage?.let { { ValidationSupportingText(it) } },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Composable
fun AppOutlinedTextFieldValidation(
    state: TextFieldState,
    label: String,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    AppOutlinedTextField(
        state = state,
        modifier = modifier,
        label = label,
        enabled = enabled,
        isError = errorMessage != null,
        supportingText = errorMessage?.let { { ValidationSupportingText(it) } },
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
    )
}

@Composable
fun ValidationSupportingText(errorMessage: String) {
    AppText(
        text = errorMessage,
        color = MaterialTheme.colorScheme.error,
    )
}