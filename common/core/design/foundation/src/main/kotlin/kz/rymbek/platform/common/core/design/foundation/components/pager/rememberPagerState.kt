package kz.rymbek.platform.common.core.design.foundation.components.pager

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable

@Composable
fun rememberPagerStateCustom(
    pageCount: Int = 3,
    initialPage: Int = 0,
) = rememberPagerState(
    initialPage = initialPage,
    initialPageOffsetFraction = 0f,
    pageCount = {
        pageCount
    }
)
