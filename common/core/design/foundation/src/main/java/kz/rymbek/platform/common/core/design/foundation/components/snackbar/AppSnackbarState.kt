package kz.rymbek.platform.common.core.design.foundation.components.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class AppSnackbarState(
    val snackbarHostState: SnackbarHostState = SnackbarHostState(),
    private val coroutineScope: CoroutineScope = MainScope(),
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
fun rememberSnackbarHostState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) = remember { snackbarHostState }

@Composable
fun rememberAppSnackbarState(
    snackbarHostState: SnackbarHostState = rememberSnackbarHostState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember {
    AppSnackbarState(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
    )
}