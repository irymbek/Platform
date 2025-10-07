package kz.rymbek.platform.common.core.design.compound.components.container.regular

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.architecture.combineStatus
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.rememberAppSnackbarState

@Composable
fun <T> AppStateScaffold(
    remote: ResultFlow<*>,
    local: ResultFlow<T> = ResultFlow.Initial,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    onActionClick: () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues, T) -> Unit,
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
) {
    BaseAppStateScaffold(
        remote = remote,
        local = local,
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        onActionClick = onActionClick,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content,
        appSnackbarState = appSnackbarState
    )
}

@Composable
fun <T> AppStateScaffold(
    remote: List<ResultFlow<*>>,
    local: ResultFlow<T> = ResultFlow.Initial,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    onActionClick: () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues, T) -> Unit,
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
) {
    val combinedRemote = remember(remote) {
        remote.combineStatus()
    }

    BaseAppStateScaffold(
        remote = combinedRemote,
        local = local,
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        onActionClick = onActionClick,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content,
        appSnackbarState = appSnackbarState
    )
}