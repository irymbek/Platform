package kz.rymbek.platform.common.base.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface NavigationInterface {
    fun navigate(key: NavKey)

    fun navigateBack()
}