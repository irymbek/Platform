package kz.rymbek.platform.common.core.design.foundation.components.date_picker.date

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.background.ThemePreviews
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun AppDatePickerPreview() {
    AppTheme {
        AppDatePicker(
            datePickerState = rememberDatePickerStateCustom()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePicker(
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState,
    dateFormatter: DatePickerFormatter = remember { DatePickerDefaults.dateFormatter() },
    title: String = "Выберите дату",
    headline: (@Composable () -> Unit)? = {
        DatePickerDefaults.DatePickerHeadline(
            selectedDateMillis = datePickerState.selectedDateMillis,
            displayMode = datePickerState.displayMode,
            dateFormatter = dateFormatter,
            modifier = Modifier.padding(
                PaddingValues(start = 24.dp, end = 12.dp, bottom = 12.dp)
            )
        )
    },
    showModeToggle: Boolean = true,
    colors: DatePickerColors = DatePickerDefaults.colors(),
) {
    DatePicker(
        modifier = modifier,
        state = datePickerState,
        dateFormatter = dateFormatter,
        title = {
            AppText(
                modifier = Modifier
                    .padding(
                        PaddingValues(start = 24.dp, end = 12.dp, bottom = 0.dp, top = 20.dp)
                    ),
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        headline = headline,
        showModeToggle = showModeToggle,
        colors = colors,
    )
}