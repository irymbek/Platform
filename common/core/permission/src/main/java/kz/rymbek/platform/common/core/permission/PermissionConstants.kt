package kz.rymbek.platform.common.core.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

object PermissionConstants {
    val STORAGE =
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )
        } else {
            emptyList()
        }

    const val CAMERA = Manifest.permission.CAMERA

    val LOCATION = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    const val NOTIFICATIONS = Manifest.permission.POST_NOTIFICATIONS
}