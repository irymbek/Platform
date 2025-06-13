package kz.rymbek.platform.common.core.design.foundation.components.date_picker.common

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
import kz.rymbek.platform.common.core.design.foundation.components.button.regular.AppTextButton

@Composable
internal fun AppDatePickerDialogCommon(
    isShow: MutableState<Boolean>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = DatePickerDefaults.shape,
    tonalElevation: Dp = DatePickerDefaults.TonalElevation,
    colors: DatePickerColors = DatePickerDefaults.colors(),
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable ColumnScope.() -> Unit
) {
    if (isShow.value) {
        DatePickerDialog(
            modifier = modifier,
            onDismissRequest = {
                isShow.value = false
            },
            confirmButton = {
                AppTextButton(
                    text = "ОК",
                    onClick = {
                        onClick()
                        isShow.value = false
                    },
                )
            },
            dismissButton = {
                AppTextButton(
                    text = "Отмена",
                    onClick = {
                        isShow.value = false
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