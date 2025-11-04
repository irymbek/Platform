package kz.rymbek.platform.common.core.design.foundation.components.date_picker.date

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.background.ThemePreviews
import kz.rymbek.platform.common.core.design.foundation.theme.AppTheme

@ThemePreviews
@Composable
fun AppDatePickerPreview() {
    AppTheme {
        AppDatePicker(
            state = rememberDatePickerStateCustom()
        )
    }
}

@Composable
fun AppDatePicker(
    modifier: Modifier = Modifier,
    state: DatePickerState,
    dateFormatter: DatePickerFormatter = remember { DatePickerDefaults.dateFormatter() },
    colors: DatePickerColors = DatePickerDefaults.colors(),
    title: (@Composable () -> Unit)? = {
        DatePickerDefaults.DatePickerTitle(
            displayMode = state.displayMode,
            modifier = Modifier.padding(PaddingValues(start = 24.dp, end = 12.dp, top = 16.dp)),
            contentColor = colors.titleContentColor,
        )
    },
    headline: (@Composable () -> Unit)? = {
        DatePickerDefaults.DatePickerHeadline(
            selectedDateMillis = state.selectedDateMillis,
            displayMode = state.displayMode,
            dateFormatter = dateFormatter,
            modifier = Modifier.padding(PaddingValues(start = 24.dp, end = 12.dp, bottom = 12.dp))
        )
    },
    showModeToggle: Boolean = true,
    focusRequester: FocusRequester? = remember { FocusRequester() }
) {
    DatePicker(
        state = state,
        modifier = modifier,
        dateFormatter = dateFormatter,
        colors = colors,
        title = title,
        headline = headline,
        showModeToggle = showModeToggle,
        focusRequester = focusRequester,
    )
}