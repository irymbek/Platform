package kz.rymbek.platform.common.core.design.foundation.components.carousel

import androidx.compose.material3.carousel.CarouselState
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberCarouselStateCustom(
    initialItem: Int = 0,
    itemCount: Int,
) = rememberCarouselState(
    initialItem = initialItem,
    itemCount = { itemCount }
)