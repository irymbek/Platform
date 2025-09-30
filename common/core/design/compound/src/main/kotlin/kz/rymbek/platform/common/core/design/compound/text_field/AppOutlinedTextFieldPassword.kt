package kz.rymbek.platform.common.core.design.compound.text_field

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.components.text_field.outlined.AppOutlinedTextField
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons

@Composable
fun AppOutlinedTextFieldPassword(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Пароль",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Password,
        imeAction = imeAction
    )

    val trailingIcon: @Composable (() -> Unit) = {
        IconButton(
            onClick = { passwordVisible = !passwordVisible },
        ) {
            val image = if (passwordVisible) {
                PlatformIcons.OutlinedVisibility
            } else {
                PlatformIcons.OutlinedVisibilityOff
            }
            AppIcon(
                imageVector = image,
                contentDescription = if (passwordVisible) "Скрыть пароль" else "Показать пароль"
            )
        }
    }

    AppOutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        shape = shape,
        colors = colors,
    )
}