package kz.rymbek.platform.common.core.design.foundation.components.list.lazy.grid

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey

@Composable
fun <T : Any> AppLazyVerticalGridPaging(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    content: @Composable (index: Int, item: T) -> Unit,
    keySelector: ((T) -> Any)?,
) {
    AppLazyVerticalGrid(
        modifier = modifier,
        content = {
            items(
                count = items.itemCount,
                key = items.itemKey(keySelector),
                itemContent = { index ->
                    val item = items[index] ?: return@items
                    content(index, item)
                }
            )
        }
    )
}