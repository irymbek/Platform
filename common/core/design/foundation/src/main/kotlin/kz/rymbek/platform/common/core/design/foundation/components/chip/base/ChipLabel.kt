package kz.rymbek.platform.common.core.design.foundation.components.chip.base

import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun ChipLabel(
    label: String
) {
    AppText(text = label)
}