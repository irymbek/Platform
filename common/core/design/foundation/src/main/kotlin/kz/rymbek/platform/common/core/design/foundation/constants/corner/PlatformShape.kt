package kz.rymbek.platform.common.core.design.foundation.constants.corner

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape

object PlatformShape {
    val small: Shape = RoundedCornerShape(PlatformCornerSize.small)
    val medium: Shape = RoundedCornerShape(PlatformCornerSize.medium)
    val large: Shape = RoundedCornerShape(PlatformCornerSize.large)
    val button: Shape = RoundedCornerShape(PlatformCornerSize.button)
}