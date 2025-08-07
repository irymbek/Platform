package kz.rymbek.platform.common.core.permission

import android.Manifest
import android.os.Build

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

    val CAMERA = listOf(Manifest.permission.CAMERA)

    val LOCATION = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    val NOTIFICATIONS =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            emptyList()
        }
}