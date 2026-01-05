package kz.rymbek.platform.common.core.design.foundation.components.carousel

import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.CarouselItemScope
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppHorizontalUncontainedCarousel(
    state: CarouselState,
    itemWidth: Dp,
    modifier: Modifier = Modifier,
    itemSpacing: Dp = 0.dp,
    flingBehavior: TargetedFlingBehavior = CarouselDefaults.noSnapFlingBehavior(),
    userScrollEnabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable CarouselItemScope.(itemIndex: Int) -> Unit
) {
    HorizontalUncontainedCarousel(
        modifier = modifier,
        state = state,
        itemWidth = itemWidth,
        itemSpacing = itemSpacing,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        contentPadding = contentPadding,
        content = content,
    )
}