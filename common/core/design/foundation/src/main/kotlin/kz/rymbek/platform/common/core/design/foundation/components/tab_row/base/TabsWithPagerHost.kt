package kz.rymbek.platform.common.core.design.foundation.components.tab_row.base

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.base.feature.architecture.ITabItem
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.pager.AppHorizontalPager

@Composable
internal fun <T : ITabItem> TabsWithPagerHost(
    tabItems: List<T>,
    modifier: Modifier = Modifier,
    createTabRow: @Composable (selectedIndex: Int, onTabClick: (Int) -> Unit) -> Unit,
    content: @Composable (page: Int) -> Unit,
    initialPage: Int = 0,
) {
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { tabItems.size }
    )
    val coroutineScope = rememberCoroutineScope()

    AppColumn(
        modifier = modifier
            .fillMaxSize(),
        content = {
            createTabRow(pagerState.currentPage) { index ->
                coroutineScope.launch { pagerState.animateScrollToPage(index) }
            }

            AppHorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(),
                content = {
                    content(it)
                }
            )
        }
    )
}