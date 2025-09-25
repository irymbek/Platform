package kz.rymbek.platform.common.core.design.foundation.components.list.lazy.grid

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey

@Composable
fun <T : Any> AppLazyVerticalGridPaging(
    modifier: Modifier = Modifier,
    columns: GridCells = GridCells.Adaptive(150.dp),
    items: LazyPagingItems<T>,
    key: ((T) -> Any)?,
    content: @Composable (index: Int, item: T) -> Unit,
) {
    AppLazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        content = {
            items(
                count = items.itemCount,
                key = items.itemKey(key = key),
                itemContent = { index ->
                    val item = items[index] ?: return@items
                    content(index, item)
                }
            )
        }
    )
}