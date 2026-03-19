package kz.rymbek.platform.common.core.design.compound.components.container.paging

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

data class PagingUiState(
    val isRefreshing: Boolean,
    val isInitialLoading: Boolean,
    val isEmpty: Boolean,
    val refreshError: Throwable?,
    val appendError: Throwable?,
    val isAppendLoading: Boolean,
) {
    val isEmptyWithError: Boolean get() = isEmpty && refreshError != null
    val hasContent: Boolean get() = !isInitialLoading && !isEmpty
}

fun <T : Any> LazyPagingItems<T>.toPagingUiState(): PagingUiState {
    val refresh = loadState.refresh
    val append = loadState.append
    val empty = itemCount == 0
    return PagingUiState(
        isRefreshing = refresh is LoadState.Loading && !empty,
        isInitialLoading = refresh is LoadState.Loading && empty,
        isEmpty = refresh is LoadState.NotLoading && empty,
        refreshError = (refresh as? LoadState.Error)?.error,
        appendError = (append as? LoadState.Error)?.error,
        isAppendLoading = append is LoadState.Loading,
    )
}