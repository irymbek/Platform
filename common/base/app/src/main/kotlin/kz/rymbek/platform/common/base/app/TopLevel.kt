package kz.rymbek.platform.common.base.app

import androidx.compose.ui.graphics.vector.ImageVector

interface TopLevel <T> {
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
    val text: String
    val route: T
}