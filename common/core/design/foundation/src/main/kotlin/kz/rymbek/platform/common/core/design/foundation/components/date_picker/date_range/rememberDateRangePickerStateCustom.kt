package kz.rymbek.platform.common.core.design.foundation.components.date_picker.date_range

import androidx.compose.material3.DisplayMode
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.date.DateUtils
import kz.rymbek.platform.common.core.design.foundation.components.date_picker.date.MySelectableDates

@Composable
fun rememberDateRangePickerStateCustom() = rememberDateRangePickerState(
    yearRange = IntRange(DateUtils.year, DateUtils.year + 1),
    initialDisplayMode = DisplayMode.Picker,
    selectableDates = MySelectableDates()
)
