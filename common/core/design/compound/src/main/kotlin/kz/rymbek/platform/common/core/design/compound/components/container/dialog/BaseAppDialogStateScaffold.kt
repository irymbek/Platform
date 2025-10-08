package kz.rymbek.platform.common.core.design.compound.components.container.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.design.compound.components.loading.LoadingDialog
import kz.rymbek.platform.common.core.design.foundation.components.scaffold.AppScaffold
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarHost
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState

@Composable
internal fun BaseAppDialogStateScaffold(
    state: ResultFlow<*>,
    onSnackbarClick: () -> Unit,
    topBar: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition,
    content: @Composable ((PaddingValues) -> Unit),
    appSnackbarState: AppSnackbarState
) {
    AppScaffold(
        modifier = modifier,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        content = { paddingValues ->
            content(paddingValues)
            when (state) {
                is ResultFlow.Loading -> LoadingDialog()
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
        snackbarHost = {
            AppSnackbarHost(
                hostState = appSnackbarState.snackbarHostState,
            )
        }
    )
}