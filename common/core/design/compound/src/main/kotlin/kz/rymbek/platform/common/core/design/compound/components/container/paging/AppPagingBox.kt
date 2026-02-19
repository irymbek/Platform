package kz.rymbek.platform.common.core.design.compound.components.container.paging

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import kz.rymbek.platform.common.core.design.foundation.components.pull_refresh.AppPullToRefreshBox
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState

/**
 * Универсальный контейнер для Paging 3 + Pull-to-Refresh
 */
@Composable
fun <T : Any> AppPagingBox(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    appSnackbarState: AppSnackbarState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    emptyContent: @Composable () -> Unit = {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Список пуст")
        }
    },
    initialLoadingContent: @Composable () -> Unit = {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    },
    content: @Composable (PaddingValues) -> Unit
) {
    val loadState = items.loadState

    // Ошибка полного обновления (refresh) показываем через snackbar
    val refreshError = loadState.refresh as? LoadState.Error
    LaunchedEffect(refreshError) {
        refreshError?.let {
            appSnackbarState.showSnackbar(
                message = "Ошибка: ${it.error.localizedMessage}",
                actionLabel = "Повторить",
                onActionClick = { items.retry() }
            )
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        when (
            loadState.refresh) {
            is LoadState.Loading if items.itemCount == 0 -> {
                initialLoadingContent()
            }

            // Список пуст после успешной загрузки
            is LoadState.NotLoading if items.itemCount == 0 -> {
                emptyContent()
            }

            // Основное состояние: Pull-to-Refresh + Список
            else -> {
                AppPullToRefreshBox(
                    isRefreshing = loadState.refresh is LoadState.Loading,
                    onRefresh = { items.refresh() },
                    modifier = Modifier.fillMaxSize()
                ) {
                    content(contentPadding)
                }
            }
        }
    }
}

/**
 * Обработчик дозагрузки (append) для LazyColumn
 */
fun LazyListScope.PagingAppendHandler(items: LazyPagingItems<*>) {
    when (val appendState = items.loadState.append) {
        is LoadState.Loading -> item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadState.Error -> item {
            val e = appendState.error
            Box(
                Modifier
                    .fillMaxWidth()
                    .clickable { items.retry() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ошибка: ${e.localizedMessage}\nНажмите, чтобы повторить",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        else -> Unit
    }
}

/**
 * Обработчик дозагрузки (append) для LazyVerticalGrid
 */
fun LazyGridScope.PagingAppendHandler(
    items: LazyPagingItems<*>,
    span: (LazyGridItemSpanScope.() -> GridItemSpan)? = { GridItemSpan(maxLineSpan) }
) {
    when (val appendState = items.loadState.append) {
        is LoadState.Loading -> item(span = span) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadState.Error -> item(span = span) {
            val e = appendState.error
            Box(
                Modifier
                    .fillMaxWidth()
                    .clickable { items.retry() }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ошибка: ${e.localizedMessage}\nНажмите, чтобы повторить",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        else -> Unit
    }
}
