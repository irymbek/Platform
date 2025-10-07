package kz.rymbek.platform.common.core.design.foundation.components.tab_row

import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.base.feature.architecture.ITabItem
import kz.rymbek.platform.common.core.design.foundation.components.divider.AppHorizontalDivider
import kz.rymbek.platform.common.core.design.foundation.components.tab_row.base.TabsWithPagerHost

@Composable
fun <T : ITabItem> AppPrimaryTabRow(
    tabItems: List<T>,
    modifier: Modifier = Modifier,
    containerColor: Color = TabRowDefaults.primaryContainerColor,
    contentColor: Color = TabRowDefaults.primaryContentColor,
    divider: @Composable () -> Unit = { AppHorizontalDivider() },
    content: @Composable (page: Int) -> Unit,
) {
    TabsWithPagerHost(
        tabItems = tabItems,
        modifier = modifier,
        createTabRow = { selectedTabIndex, onClick ->
            PrimaryTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = containerColor,
                contentColor = contentColor,
                divider = divider,
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