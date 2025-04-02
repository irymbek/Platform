package kz.rymbek.platform.common.core.design.foundation.components.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions

@Composable
fun AppSpacer(
    size: Dp = Dimensions.defaultPaddingDp,
) {
    Spacer(
        modifier = Modifier
            .size(size)
    )
}