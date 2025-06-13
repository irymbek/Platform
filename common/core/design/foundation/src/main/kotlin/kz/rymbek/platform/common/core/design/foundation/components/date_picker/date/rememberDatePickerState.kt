package kz.rymbek.platform.common.core.design.foundation.components.date_picker.date

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.date.DateUtils

@Composable
fun rememberDatePickerStateCustom(): DatePickerState {
    return rememberDatePickerState(
        initialSelectedDateMillis = DateUtils.milliseconds,
        yearRange = IntRange(DateUtils.year, DateUtils.year + 1),
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = MySelectableDates()
    )
}

class MySelectableDates : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis >= DateUtils.dayStartMilliseconds
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year <= DateUtils.year + 1
    }
}