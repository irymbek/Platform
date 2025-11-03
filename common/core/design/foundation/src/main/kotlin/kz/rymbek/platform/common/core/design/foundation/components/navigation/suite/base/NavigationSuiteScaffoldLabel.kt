package kz.rymbek.platform.common.core.design.foundation.components.navigation.suite.base

import android.R.attr.label
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun NavigationSuiteScaffoldLabel(
    label: String,
) {
    AppText(
        text = label,
        style = MaterialTheme.typography.labelSmall
    )
}