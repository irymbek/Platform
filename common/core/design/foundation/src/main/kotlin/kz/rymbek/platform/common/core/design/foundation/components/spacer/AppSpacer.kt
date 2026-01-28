package kz.rymbek.platform.common.core.design.foundation.components.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

@Composable
fun AppSpacer(
    modifier: Modifier = Modifier
        .size(PlatformPaddings.default)
) {
    Spacer(
        modifier = modifier
    )
}