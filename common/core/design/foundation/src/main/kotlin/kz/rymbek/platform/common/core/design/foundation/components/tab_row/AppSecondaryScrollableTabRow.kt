package kz.rymbek.platform.common.core.design.foundation.components.tab_row

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kz.rymbek.platform.common.base.feature.architecture.ITabItem
import kz.rymbek.platform.common.core.design.foundation.components.divider.AppHorizontalDivider
import kz.rymbek.platform.common.core.design.foundation.components.tab_row.base.AppTabRowDefaults
import kz.rymbek.platform.common.core.design.foundation.components.tab_row.base.TabsWithPagerHost

@Composable
fun <T : ITabItem> AppSecondaryScrollableTabRow(
    tabItems: List<T>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    containerColor: Color = TabRowDefaults.secondaryContainerColor,
    contentColor: Color = TabRowDefaults.secondaryContentColor,
    edgePadding: Dp = AppTabRowDefaults.ScrollableTabRowEdgeStartPadding,
    divider: @Composable () -> Unit = { AppHorizontalDivider() },
    minTabWidth: Dp = TabRowDefaults.ScrollableTabRowMinTabWidth,
    content: @Composable (page: Int) -> Unit,
) {
    TabsWithPagerHost(
        tabItems = tabItems,
        modifier = modifier,
        createTabRow = { selectedTabIndex, onClick ->
            SecondaryScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                scrollState = scrollState,
                containerColor = containerColor,
                contentColor = contentColor,
                edgePadding = edgePadding,
                divider = divider,
                minTabWidth = minTabWidth,
                tabs = {
                    tabItems.forEachIndexed { index, item ->
                        AppTab(
                            selected = selectedTabIndex == index,
                            onClick = { onClick(index) },
                            title = item.title,
                        )
                    }
                }
            )
        },
        content = content
    )
}