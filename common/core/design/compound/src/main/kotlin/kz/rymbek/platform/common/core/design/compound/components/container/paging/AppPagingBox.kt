package kz.rymbek.platform.common.core.design.compound.components.container.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.LazyPagingItems
import kz.rymbek.platform.common.core.design.foundation.components.button.AppTextButton
import kz.rymbek.platform.common.core.design.foundation.components.button.base.ButtonContent
import kz.rymbek.platform.common.core.design.foundation.components.container.AppBox
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.container.AppRow
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppCircularProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.pull_refresh.AppPullToRefreshBox
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

// AppPagingBox.kt
@Composable
fun <T : Any> AppPagingBox(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    appSnackbarState: AppSnackbarState,
    emptyContent: @Composable () -> Unit = { ScreenEmptyContent() },
    loadingContent: @Composable () -> Unit = { InitialLoadingContent() },
    content: @Composable () -> Unit,
) {
    val state = items.toPagingUiState()

    // Используем referential identity для Throwable,
    // иначе два одинаковых исключения не перезапустят эффект
    LaunchedEffect(state.refreshError) {
        val error = state.refreshError ?: return@LaunchedEffect
        if (state.hasContent) {
            appSnackbarState.showSnackbar(
                message = "Ошибка: ${error.localizedMessage}",
                actionLabel = "Повторить",
                onActionClick = { items.retry() }
            )
        }
    }

    // Единственный PullToRefreshBox на весь компонент (DRY)
    // isRefreshing = true только когда есть контент + идёт обновление
    AppPullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = { items.refresh() },
        modifier = modifier.fillMaxSize(),
    ) {
        when {
            state.isInitialLoading -> {
                // Первая загрузка — pull-to-refresh не нужен, просто спиннер
                loadingContent()
            }

            state.isEmptyWithError -> {
                // Ошибка на пустом экране — verticalScroll обязателен,
                // иначе NestedScrollConnection не получит жесты
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center,
                ) {
                    ErrorContentColumn(
                        text = "Ошибка загрузки.\nПотяните вниз или нажмите «Повторить»",
                        onClick = { items.retry() }
                    )
                }
            }

            state.isEmpty -> {
                // Пустой список — аналогично нужен scroll для жестов
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center,
                ) {
                    emptyContent()
                }
            }

            else -> {
                // LazyList/LazyGrid сами реализуют NestedScrollConnection
                content()
            }
        }
    }
}


// pagingAppendItem.kt
// Передаём готовый state снаружи — не пересчитываем внутри
fun LazyListScope.pagingAppendItem(
    state: PagingUiState,
    onRetry: () -> Unit,
) {
    when {
        state.isAppendLoading -> item(key = "paging_append_loading") {
            AppendLoadingContent()
        }
        state.appendError != null -> item(key = "paging_append_error") {
            ErrorContentRow(onClick = onRetry)
        }
    }
}

fun LazyGridScope.pagingAppendItem(
    state: PagingUiState,
    onRetry: () -> Unit,
    span: (LazyGridItemSpanScope.() -> GridItemSpan)? = { GridItemSpan(maxLineSpan) },
) {
    when {
        state.isAppendLoading -> item(key = "paging_append_loading", span = span) {
            AppendLoadingContent()
        }
        state.appendError != null -> item(key = "paging_append_error", span = span) {
            ErrorContentRow(onClick = onRetry)
        }
    }
}

@Composable
private fun AppendLoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PlatformPaddings.default),
        contentAlignment = Alignment.Center,
    ) {
        AppCircularProgressIndicator(
            modifier = Modifier.size(PlatformIconSize.lg)
        )
    }
}

@Composable
fun ScreenEmptyContent(
    text: String = "Данных нет"
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
                onClick = onClick,
                content = {
                    ButtonContent(text = "Повторить")
                }
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
                onClick = onClick,
                content = {
                    ButtonContent(text = "Повторить попытку")
                }
            )
        }
    )
}
