package kz.rymbek.platform.common.core.design.compound.components.container.regular

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppLinearProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.scaffold.AppScaffold
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarHost
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.rememberAppSnackbarState

@Composable
internal fun <T> BaseAppStateScaffold(
    remote: ResultFlow<*>,
    local: ResultFlow<T>,
    modifier: Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
    onSnackbarClick: () -> Unit,
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition,
    containerColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues, T) -> Unit,
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
                            onActionClick = onSnackbarClick
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
                        onActionClick = onSnackbarClick
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
    val message = exception.message
    var shown by rememberSaveable(message) { mutableStateOf(false) }

    LaunchedEffect(message) {
        if (!shown) {
            shown = true
            appSnackbarState.showSnackbar(
                message = "Ошибка: $message",
                actionLabel = "Скрыть",
                onActionClick = onActionClick
            )
        }
    }
}