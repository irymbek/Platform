package kz.rymbek.platform.common.core.design.foundation.components.list.lazy.column

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import kz.rymbek.platform.common.core.architecture.interfaces.Identifiable

@Composable
fun <T : Identifiable> AppLazyColumnPaging(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    content: @Composable (
        index: Int,
        item: T,
    ) -> Unit,
    headerContent: @Composable (LazyItemScope.() -> Unit)? = null,
) {
    AppLazyColumn(
        modifier = modifier,
        content = {
            headerContent?.let {
                item(
                    content = it,
                )
            }

            items(
                count = items.itemCount,
                key = items.itemKey { it.id },
                itemContent = { index ->
                    items[index]?.let {
                        content(
                            index,
                            it
                        )
                    }
                }
            )
        }
    )
}