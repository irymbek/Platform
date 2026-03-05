package kz.rymbek.platform.common.core.design.compound.components.container.regular

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.design.compound.components.container.paging.ErrorContentColumn
import kz.rymbek.platform.common.core.design.compound.components.container.paging.InitialLoadingContent
import kz.rymbek.platform.common.core.design.compound.components.container.paging.ScreenEmptyContent
import kz.rymbek.platform.common.core.design.foundation.components.container.AppBox
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppLinearProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.pull_refresh.AppPullToRefreshBox
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState

@Composable
fun <T> AppStateContainer(
    local: ResultFlow<T>,
    remote: ResultFlow<*>,
    appSnackbarState: AppSnackbarState,
    modifier: Modifier = Modifier,
    onRetryLocal: () -> Unit = {},
    onRefresh: () -> Unit,
    initialContent: @Composable () -> Unit = { InitialLoadingContent() },
    emptyContent: @Composable () -> Unit = { ScreenEmptyContent() },
    content: @Composable (T) -> Unit
) {
    val remoteError = (remote as? ResultFlow.Error)?.exception
    val isRefreshing = remote is ResultFlow.Loading

    RemoteErrorEffect(
        error = remoteError,
        appSnackbarState = appSnackbarState,
        onRetry = onRefresh
    )

    AppBox(
        modifier = modifier
            .fillMaxSize(),
        content = {
            ResultFlowHandler(
                result = local,
                initial = initialContent,
                loading = initialContent,
                empty = {
                    AppPullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = onRefresh,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        emptyContent()
                    }
                },
                error = { exception ->

                    ErrorContentColumn(
                        text = exception.localizedMessage ?: "Ошибка БД",
                        onClick = onRetryLocal
                    )

                },
                success = { data ->

                    AppPullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = onRefresh,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        content(data)

                    }

                }
            )

            RemoteLoadingOverlay(isRefreshing)
        }
    )
}

@Composable
fun RemoteErrorEffect(
    error: Throwable?,
    appSnackbarState: AppSnackbarState,
    onRetry: () -> Unit
) {
    LaunchedEffect(error) {
        error?.let {
            appSnackbarState.showSnackbar(
                message = "Ошибка обновления: ${it.localizedMessage}",
                actionLabel = "Повторить",
                onActionClick = onRetry
            )
        }
    }
}

@Composable
private fun BoxScope.RemoteLoadingOverlay(
    isRefreshing: Boolean
) {

    AnimatedVisibility(
        visible = isRefreshing,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
        modifier = Modifier.align(Alignment.TopCenter)
    ) {

        AppLinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )

    }

}