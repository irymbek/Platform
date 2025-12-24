package kz.rymbek.platform.common.core.design.foundation.components.text_field.base

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion

val SecureTextFieldKeyboardOptions =
    KeyboardOptions(autoCorrectEnabled = false, keyboardType = KeyboardType.Password)

const val DefaultObfuscationCharacter: Char = '\u2022'