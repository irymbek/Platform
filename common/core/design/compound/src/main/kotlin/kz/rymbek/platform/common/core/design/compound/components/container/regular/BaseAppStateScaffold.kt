package kz.rymbek.platform.common.core.design.compound.components.container.regular

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppLinearProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.scaffold.AppScaffold
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarHost
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState

@Composable
internal fun <T> BaseAppStateScaffold(
    remote: ResultFlow<*>,
    local: ResultFlow<T>,
    modifier: Modifier,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    onActionClick: () -> Unit,
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition,
    containerColor: Color,
    contentColor: Color,
    contentWindowInsets: WindowInsets,
    content: @Composable (PaddingValues, T) -> Unit,
    appSnackbarState: AppSnackbarState
) {
    AppScaffold(
        modifier = modifier,
        topBar = {
            AppColumn {
                topBar()
                ResultFlowHandler(
                    result = remote,
                    loading = {
                        AppLinearProgressIndicator(
                            Modifier.fillMaxWidth()
                        )
                    },
                    error = { exception ->
                        HandleError(
                            exception = exception,
                            appSnackbarState = appSnackbarState,
                            onActionClick = onActionClick
                        )
                    },
                    success = { }
                )
            }
        },
        bottomBar = bottomBar,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        content = { paddingValues ->
            ResultFlowHandler(
                result = local,
                loading = { /* можно добавить shimmer или что-то своё */ },
                error = { exception ->
                    HandleError(
                        exception = exception,
                        appSnackbarState = appSnackbarState,
                        onActionClick = onActionClick
                    )
                },
                success = { data ->
                    content(
                        paddingValues,
                        data
                    )
                }
            )
        },
        snackbarHost = {
            AppSnackbarHost(
                hostState = appSnackbarState.snackbarHostState,
            )
        }
    )
}

@Composable
fun <T> ResultFlowHandler(
    result: ResultFlow<T>,
    loading: @Composable () -> Unit = {},
    error: @Composable (Throwable) -> Unit = {},
    empty: @Composable () -> Unit = {},
    initial: @Composable () -> Unit = {},
    success: @Composable (T) -> Unit
) {
    when (result) {
        is ResultFlow.Initial -> initial()
        is ResultFlow.Loading -> loading()
        is ResultFlow.Error   -> error(result.exception)
        is ResultFlow.Empty   -> empty()
        is ResultFlow.Success -> success(result.data)
    }
}

@Composable
private fun HandleError(
    exception: Throwable,
    appSnackbarState: AppSnackbarState,
    onActionClick: () -> Unit
) {
    LaunchedEffect(exception) {
        appSnackbarState.showSnackbar(
            message = "Ошибка: ${exception.message}",
            actionLabel = "Скрыть",
            onActionClick = onActionClick
        )
    }
}