package kz.rymbek.platform.common.base.navigation

import androidx.navigation3.runtime.NavKey

interface Navigator {
    fun navigate(key: NavKey)

    fun navigateBack()
}
