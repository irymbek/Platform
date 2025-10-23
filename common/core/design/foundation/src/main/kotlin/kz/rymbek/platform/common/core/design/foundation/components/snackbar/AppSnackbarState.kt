package kz.rymbek.platform.common.core.design.foundation.components.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class AppSnackbarState(
    val snackbarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
) {
    fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
        onActionClick: () -> Unit = {},
    ) {
        coroutineScope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration,
            )

            when (result) {
                SnackbarResult.Dismissed -> {

                }
                SnackbarResult.ActionPerformed -> {
                    onActionClick()
                }
            }
        }
    }
}

@Composable
fun rememberSnackbarHostState() = remember { SnackbarHostState() }

@Composable
fun rememberAppSnackbarState(
    snackbarHostState: SnackbarHostState = rememberSnackbarHostState(),
): AppSnackbarState {
    val coroutineScope = rememberCoroutineScope()
    return remember(snackbarHostState) {
        AppSnackbarState(
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope,
        )
    }
}