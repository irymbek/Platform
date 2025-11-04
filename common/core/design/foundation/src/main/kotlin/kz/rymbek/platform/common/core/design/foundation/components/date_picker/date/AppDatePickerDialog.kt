package kz.rymbek.platform.common.core.design.foundation.components.date_picker.date

import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties
import kz.rymbek.platform.common.core.design.foundation.components.date_picker.dialog.AppDatePickerDialog

@Composable
fun AppDatePickerDialog(
    isVisible: MutableState<Boolean>,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState = rememberDatePickerStateCustom(),
    shape: Shape = DatePickerDefaults.shape,
    tonalElevation: Dp = DatePickerDefaults.TonalElevation,
    colors: DatePickerColors = DatePickerDefaults.colors(),
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
) {
    AppDatePickerDialog(
        isVisible = isVisible,
        modifier = modifier,
        shape = shape,
        tonalElevation = tonalElevation,
        colors = colors,
        properties = properties,
        onClick = {
            onDateSelected(datePickerState.selectedDateMillis)
        },
        content = {
            AppDatePicker(
                state = datePickerState,
            )
        },
    )
}