package kz.rymbek.platform.common.core.design.foundation.components.list.lazy.grid

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import kz.rymbek.platform.common.core.architecture.interfaces.Identifiable

@Composable
fun <T : Identifiable> AppLazyVerticalGridPaging(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    content: @Composable (index: Int, item: T) -> Unit,
) {
    AppLazyVerticalGrid(
        modifier = modifier,
        content = {
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