package kz.rymbek.platform.common.core.design.foundation.components.date_picker.date_range

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.DateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformDimensions

@Composable
fun AppDateRangePicker(
    modifier: Modifier = Modifier,
    dateRangePickerState: DateRangePickerState = rememberDateRangePickerStateCustom(),
    dateFormatter: DatePickerFormatter = remember { DatePickerDefaults.dateFormatter() },
    title: (@Composable () -> Unit)? = {
        DateRangePickerDefaults.DateRangePickerTitle(
            displayMode = dateRangePickerState.displayMode,
            modifier = Modifier.padding(
                top = PlatformDimensions.defaultPaddingDp,
                start = PlatformDimensions.contentPaddingDp,
            )
        )
    },
    headline: (@Composable () -> Unit)? = {
        DateRangePickerDefaults.DateRangePickerHeadline(
            selectedStartDateMillis = dateRangePickerState.selectedStartDateMillis,
            selectedEndDateMillis = dateRangePickerState.selectedEndDateMillis,
            displayMode = dateRangePickerState.displayMode,
            dateFormatter,
            modifier = Modifier.padding(
                PaddingValues(
                    top = PlatformDimensions.defaultPaddingDp,
                    start = PlatformDimensions.defaultPaddingDp,
                    bottom = PlatformDimensions.defaultPaddingDp,
                )
            )
        )
    },
    showModeToggle: Boolean = true,
    colors: DatePickerColors = DatePickerDefaults.colors()
) {
    DateRangePicker(
        state = dateRangePickerState,
        modifier = modifier,
        dateFormatter = dateFormatter,
        title = title,
        headline = headline,
        showModeToggle = showModeToggle,
        colors = colors,
    )
}