package kz.rymbek.platform.common.core.permission

import kz.rymbek.platform.common.core.design.foundation.components.snackbar.AppSnackbarState

interface PermissionHandler {
    val appSnackbarState: AppSnackbarState

    fun requestPermissions(
        onGranted: () -> Unit = {},
    )
}