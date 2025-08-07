package kz.rymbek.platform.common.core.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kz.rymbek.platform.common.core.activity.ActivityUtils
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState
import kz.rymbek.platform.common.core.design.foundation.components.snackbar.rememberAppSnackbarState

@Composable
fun rememberPermissions(
    permissions: List<String>,
    onResult: (Map<String, Boolean>) -> Unit = {}
): PermissionHandler {
    val context = LocalContext.current
    val activityUtils = remember { ActivityUtils(context) }
    val multiplePermissionsState = rememberMultiplePermissionsState(
        permissions = permissions,
        onPermissionsResult = onResult,
    )

    val appSnackbarState = rememberAppSnackbarState()

    return remember(multiplePermissionsState) {
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
