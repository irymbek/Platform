package kz.rymbek.platform.common.core.design.compound.components.container.dataresult

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.architecture.DataResult
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.progressindicator.AppLinearProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.scaffold.AppScaffold
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarHost
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.rememberAppSnackbarState

@Composable
fun <T> AppDataScaffold(
    result: DataResult<T>,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Transparent,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues, T) -> Unit,
) {
    AppScaffold(
        modifier = modifier,
        topBar = {
            AppColumn {
                topBar()
                if (result is DataResult.Loading) {
                    AppLinearProgressIndicator(Modifier.fillMaxWidth())
                }
            }
        },
        bottomBar = bottomBar,
        containerColor = containerColor,
        contentWindowInsets = contentWindowInsets,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        snackbarHost = { AppSnackbarHost(appSnackbarState.snackbarHostState) },
        content = { paddingValues ->
            DataResultHandler(
                result = result,
                loading = {  },
                error = { exception ->
                    HandleError(
                        exception = exception,
                        appSnackbarState = appSnackbarState,
                    )
                },
                success = { data -> content(paddingValues, data) }
            )
        }
    )
}

@Composable
private fun HandleError(
    exception: Throwable,
    appSnackbarState: AppSnackbarState,
    onActionClick: () -> Unit ={}
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