package kz.rymbek.platform.common.core.design.foundation.components.date_picker.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties
import kz.rymbek.platform.common.core.design.foundation.components.button.AppTextButton

@Composable
internal fun BaseDatePickerDialog(
    isVisible: MutableState<Boolean>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = DatePickerDefaults.shape,
    tonalElevation: Dp = DatePickerDefaults.TonalElevation,
    colors: DatePickerColors = DatePickerDefaults.colors(),
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable ColumnScope.() -> Unit
) {
    if (isVisible.value) {
        DatePickerDialog(
            onDismissRequest = {
                isVisible.value = false
            },
            confirmButton = {
                AppTextButton(
                    text = "ОК",
                    onClick = {
                        onClick()
                        isVisible.value = false
                    },
                )
            },
            modifier = modifier,
            dismissButton = {
                AppTextButton(
                    text = "Отмена",
                    onClick = {
                        isVisible.value = false
                    },
                )
            },
            shape = shape,
            tonalElevation = tonalElevation,
            colors = colors,
            properties = properties,
            content = content
        )
    }
}