package kz.rymbek.platform.common.core.design.compound.text_field

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.components.icon_button.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.components.text_field.secure.AppOutlinedSecureTextField
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons

@Composable
fun AppOutlinedTextFieldPassword(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    label: String = "Пароль",
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    AppOutlinedSecureTextField(
        state = state,
        modifier = modifier,
        label = label,
        textObfuscationMode =
            if (passwordHidden) TextObfuscationMode.RevealLastTyped
            else TextObfuscationMode.Visible,
        trailingIcon = {
            val icon = if (passwordHidden) {
                PlatformIcons.OutlinedVisibility
            } else {
                PlatformIcons.OutlinedVisibilityOff
            }
            AppIconButton(
                icon = icon,
                onClick = { passwordHidden = !passwordHidden }
            )
        },
    )
}