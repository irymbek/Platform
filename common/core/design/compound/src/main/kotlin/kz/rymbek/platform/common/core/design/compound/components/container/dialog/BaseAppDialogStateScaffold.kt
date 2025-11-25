package kz.rymbek.platform.common.core.design.compound.components.container.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.design.compound.components.loading.LoadingDialog
import kz.rymbek.platform.common.core.design.foundation.components.scaffold.AppScaffold
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarHost
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.rememberAppSnackbarState

@Composable
internal fun BaseAppDialogStateScaffold(
    state: ResultFlow<*>,
    modifier: Modifier = Modifier,
    message: String? = null,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
    onSnackbarClick: () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit,
) {
    AppScaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = {
            AppSnackbarHost(
                hostState = appSnackbarState.snackbarHostState,
            )
        },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = { paddingValues ->
            content(paddingValues)
            when (state) {
                is ResultFlow.Loading -> LoadingDialog(message = message)
                is ResultFlow.Error -> {
                    appSnackbarState.showSnackbar(
                        message = "Ошибка: ${state.exception.message}",
                        actionLabel = "Скрыть",
                        onActionClick = onSnackbarClick
                    )
                }

                is ResultFlow.Success,
                is ResultFlow.Empty,
                is ResultFlow.Initial -> Unit
            }
        },
    )
}