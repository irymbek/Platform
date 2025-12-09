package kz.rymbek.platform.common.core.design.foundation.components.time

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerDialogDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.window.DialogProperties
import kz.rymbek.platform.common.core.design.foundation.components.button.AppTextButton
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.components.icon.toggle.AppIconToggleButton
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun AppTimePickerDialog(
    state: TimePickerState,
    isVisible: MutableState<Boolean>,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "Выберите время",
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    shape: Shape = TimePickerDialogDefaults.shape,
    containerColor: Color = TimePickerDialogDefaults.containerColor,
    colors: TimePickerColors = TimePickerDefaults.colors(),
) {
    var showDial by remember { mutableStateOf(true) }

    val toggleIcon = if (showDial) {
        Icons.Filled.Keyboard
    } else {
        Icons.Filled.Schedule
    }

    TimePickerDialog(
        onDismissRequest = {
            isVisible.value = false
        },
        confirmButton = {
            AppTextButton(
                text = "ОК",
                onClick = {
                    isVisible.value = false
                    onConfirm()
                }
            )
        },
        title = { AppText(title) },
        modifier = modifier,
        properties = properties,
        modeToggleButton = {
            AppIconToggleButton(
                checked = showDial,
                onCheckedChange = { showDial = it },
                content = {
                    AppIcon(
                        imageVector = toggleIcon,
                    )
                }
            )
        },
        dismissButton = {
            AppTextButton(
                text = "Отмена",
                onClick = { isVisible.value = false },
            )
        },
        shape = shape,
        containerColor = containerColor,
        content = {
            if (showDial) {
                AppTimePicker(
                    state = state,
                    colors = colors,
                )
            } else {
                AppTimeInput(
                    state = state,
                    colors = colors,
                )
            }
        },
    )
}