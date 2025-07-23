package kz.rymbek.platform.common.base.work

import androidx.work.Constraints
import androidx.work.NetworkType

object Constraints {
    val UploadConstraints
        get() = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
}