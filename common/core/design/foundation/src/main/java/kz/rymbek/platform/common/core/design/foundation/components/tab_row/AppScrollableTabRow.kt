package kz.rymbek.platform.common.core.design.foundation.components.tab_row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.divider.AppHorizontalDivider
import kz.rymbek.platform.common.core.design.foundation.components.pager.AppHorizontalPager

@Composable
fun AppScrollableTabRow(
    modifier: Modifier = Modifier,
    tabItems: List<String>,
    containerColor: Color = TabRowDefaults.primaryContainerColor,
    contentColor: Color = TabRowDefaults.primaryContentColor,
    edgePadding: Dp = 0.dp,
    divider: @Composable () -> Unit = @Composable { AppHorizontalDivider() },
    content: @Composable (Int) -> Unit,
    onSelectedTabChanged: (index: Int) -> Unit = {},
) {

    val pagerState = rememberPagerState {
        tabItems.size
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onSelectedTabChanged(page)
        }
    }



    AppColumn(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            ScrollableTabRow(
                modifier = modifier,
                containerColor = containerColor,
                contentColor = contentColor,
                edgePadding = edgePadding,
                selectedTabIndex = pagerState.currentPage,
                divider = divider,
                tabs = {
                    tabItems.forEachIndexed { index, title ->
                        AppTab(
                            coroutineScope = coroutineScope,
                            pagerState = pagerState,
                            index = index,
                            title = title,
                        )
                    }
                }
            )

            AppHorizontalPager(
                modifier = Modifier
                    .fillMaxWidth(),
                state = pagerState,
                content = { index ->
                    content(index)
                }
            )
        }
    )
}