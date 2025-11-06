package kz.rymbek.platform.common.core.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kz.rymbek.platform.common.core.activity.rememberActivityUtils
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.rememberAppSnackbarState

@Composable
fun rememberPermissions(
    permissions: List<String>,
    appSnackbarState: AppSnackbarState = rememberAppSnackbarState(),
    onResult: (Map<String, Boolean>) -> Unit = {},
    onAllGranted: () -> Unit = {}
): PermissionHandler {
    val activityUtils = rememberActivityUtils()
    val multiplePermissionsState = rememberMultiplePermissionsState(
        permissions = permissions,
        onPermissionsResult = {
            onResult(it)
            if (it.values.all { granted -> granted }) onAllGranted()
        },
    )

    return remember(appSnackbarState, multiplePermissionsState) {
        object : PermissionHandler {
            override val appSnackbarState: AppSnackbarState = appSnackbarState

            override fun requestPermissions(
                onGranted: () -> Unit,
            ) {
                when {
                    multiplePermissionsState.allPermissionsGranted -> onGranted()
                    multiplePermissionsState.shouldShowRationale -> {
                        appSnackbarState.showSnackbar(
                            message = "Необходимо предоставить разрешения",
                            actionLabel = "Перейти",
                            onActionClick = {
                                activityUtils.openAppSettings()
                            }
                        )
                    }
                    else -> multiplePermissionsState.launchMultiplePermissionRequest()
                }
            }
        }
    }
}
