package kz.rymbek.platform.common.core.design.compound.components.container.dialog

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.architecture.combineStatus
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.rememberAppSnackbarState

@Composable
fun AppDialogStateScaffold(
    resultFlow: ResultFlow<*>,
    onSnackbarClick: () -> Unit = {},
    topBar: @Composable (() -> Unit) = {},
    floatingActionButton:  @Composable (ColumnScope.() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable ((PaddingValues) -> Unit),
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
) {
    BaseAppDialogStateScaffold(
        state = resultFlow,
        onSnackbarClick = onSnackbarClick,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        content = content,
        appSnackbarState = appSnackbarState
    )
}

@Composable
fun AppDialogStateScaffold(
    resultFlows: List<ResultFlow<*>>,
    onSnackbarClick: () -> Unit = {},
    topBar: @Composable (() -> Unit) = {},
    floatingActionButton:  @Composable (ColumnScope.() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable ((PaddingValues) -> Unit),
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
) {
    BaseAppDialogStateScaffold(
        state = resultFlows.combineStatus(),
        onSnackbarClick = onSnackbarClick,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        content = content,
        appSnackbarState = appSnackbarState
    )
}

@Composable
fun AppDialogStateScaffold(
    vararg resultFlows: ResultFlow<*>,
    onSnackbarClick: () -> Unit = {},
    topBar: @Composable (() -> Unit) = {},
    floatingActionButton:  @Composable (ColumnScope.() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable ((PaddingValues) -> Unit),
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
) {
    val combined = remember(resultFlows) {
        resultFlows.toList().combineStatus()
    }

    BaseAppDialogStateScaffold(
        state = combined,
        onSnackbarClick = onSnackbarClick,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        content = content,
        appSnackbarState = appSnackbarState
    )
}
