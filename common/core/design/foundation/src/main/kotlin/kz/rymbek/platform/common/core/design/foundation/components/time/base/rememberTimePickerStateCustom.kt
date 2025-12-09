package kz.rymbek.platform.common.core.design.foundation.components.time.base

import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.date.DateUtils

@Composable
fun rememberTimePickerStateCustom(
    initialHour: Int = DateUtils.hour,
    initialMinute: Int = DateUtils.minute,
    is24Hour: Boolean = true,
) = rememberTimePickerState(
    initialHour = initialHour,
    initialMinute = initialMinute,
    is24Hour = is24Hour,
)
