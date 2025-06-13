package kz.rymbek.platform.common.core.design.foundation.constants

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimensions {
    val elementPaddingDp: Dp = 5.dp
    val defaultPaddingDp: Dp = 8.dp
    val contentPaddingDp: Dp = 16.dp
    val largePaddingDp: Dp = 48.dp

    val iconSmall: Dp = 24.dp
    val iconMedium: Dp = 36.dp
    val iconLarge: Dp = 48.dp
    val iconBig: Dp = 60.dp

    val mediumCornerRadius: Dp = 10.dp
    val smallCornerRadius: Dp = 5.dp
    val smallCornerShape = RoundedCornerShape(smallCornerRadius)

    val buttonCornerRadius: Dp = 10.dp
}