package kz.rymbek.platform.common.core.design.foundation.components.tab_row

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
)