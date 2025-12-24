package kz.rymbek.platform.common.base.app

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

interface TopLevel {
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
    val text: String
    val navKey: NavKey
}