package kz.rymbek.platform.common.core.design.compound.components.container.paging

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import kz.rymbek.platform.common.core.design.foundation.components.button.AppTextButton
import kz.rymbek.platform.common.core.design.foundation.components.container.AppBox
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.container.AppRow
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppCircularProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.pull_refresh.AppPullToRefreshBox
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

@Composable
fun <T : Any> AppPagingBox(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    appSnackbarState: AppSnackbarState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    emptyContent: @Composable () -> Unit = { ScreenEmptyContent() },
    initialLoadingContent: @Composable () -> Unit = { InitialLoadingContent() },
    content: @Composable (PaddingValues) -> Unit
) {
    val loadState = items.loadState

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

            is LoadState.NotLoading if items.itemCount == 0 -> {
                emptyContent()
            }

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

fun LazyGridScope.PagingAppendHandler(
    items: LazyPagingItems<*>,
    span: (LazyGridItemSpanScope.() -> GridItemSpan)? = { GridItemSpan(maxLineSpan) }
) {
    when (val appendState = items.loadState.append) {
        is LoadState.Loading -> item(span = span) {
            AppBox(
                Modifier
                    .fillMaxWidth()
                    .padding(PlatformPaddings.default),
                contentAlignment = Alignment.Center,
                content = {
                    AppCircularProgressIndicator(
                        modifier = Modifier
                            .size(PlatformIconSize.lg)
                    )
                }
            )
        }

        is LoadState.Error -> item(span = span) {
            ErrorContentRow(
                onClick = { items.retry() }
            )
        }

        else -> Unit
    }
}

@Composable
fun ScreenEmptyContent(
    text: String = "Список пуск"
) {
    AppBox(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            AppText(
                text = text,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    )
}

@Composable
fun InitialLoadingContent(
    content: @Composable (BoxScope.() -> Unit) = {
        CircularProgressIndicator()
    }
) {
    AppBox(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun ErrorContentRow(
    onClick: () -> Unit,
) {
    AppRow(
        content = {
            AppText(
                modifier = Modifier
                    .weight(1F),
                text = "Ошибка загрузки",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.outline
            )

            AppTextButton(
                text = "Повторить",
                onClick = onClick
            )
        }
    )
}

@Composable
fun ErrorContentColumn(
    text: String = "Ошибка загрузки",
    onClick: () -> Unit,
) {
    AppColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            AppText(
                text = text,
                color = MaterialTheme.colorScheme.outline
            )

            AppTextButton(
                text = "Повторить попытку",
                onClick = onClick
            )
        }
    )
}
