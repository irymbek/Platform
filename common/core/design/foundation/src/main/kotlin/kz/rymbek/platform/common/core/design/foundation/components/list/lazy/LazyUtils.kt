package kz.rymbek.platform.common.core.design.foundation.components.list.lazy

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey

fun <T : Any> LazyListScope.pagingItems(
    items: LazyPagingItems<T>,
    key: ((T) -> Any)? = null,
    contentType: (Int) -> Any? = { null },
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    items(
        count = items.itemCount,
        key = items.itemKey(key),
        contentType = contentType,
        itemContent = { index ->
            val item = items[index] ?: return@items
            itemContent(index, item)
        }
    )
}

fun <T : Any> LazyGridScope.pagingItems(
    items: LazyPagingItems<T>,
    key: ((T) -> Any)? = null,
    span: (LazyGridItemSpanScope.(Int) -> GridItemSpan)? = null,
    contentType: (Int) -> Any? = { null },
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    items(
        count = items.itemCount,
        key = items.itemKey(key = key),
        span = span,
        contentType = contentType,
        itemContent = { index ->
            val item = items[index] ?: return@items
            itemContent(index, item)
        }
    )
}