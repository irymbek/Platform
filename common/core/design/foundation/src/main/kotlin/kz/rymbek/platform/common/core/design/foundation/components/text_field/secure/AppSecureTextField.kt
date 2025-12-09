package kz.rymbek.platform.common.core.design.foundation.components.text_field.secure

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.material3.TextFieldLabelScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density

@Composable
fun AppSecureTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    labelPosition: TextFieldLabelPosition = TextFieldLabelPosition.Attached(),
    label: @Composable (TextFieldLabelScope.() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    inputTransformation: InputTransformation? = null,
    textObfuscationMode: TextObfuscationMode = TextObfuscationMode.RevealLastTyped,
    onKeyboardAction: KeyboardActionHandler? = null,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    contentPadding: PaddingValues =
        if (label == null || labelPosition is TextFieldLabelPosition.Above) {
            TextFieldDefaults.contentPaddingWithoutLabel()
        } else {
            TextFieldDefaults.contentPaddingWithLabel()
        },
    interactionSource: MutableInteractionSource? = null,
) {
    SecureTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        textStyle = textStyle,
        labelPosition = labelPosition,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        inputTransformation = inputTransformation,
        textObfuscationMode = textObfuscationMode,
        onKeyboardAction = onKeyboardAction,
        onTextLayout = onTextLayout,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}