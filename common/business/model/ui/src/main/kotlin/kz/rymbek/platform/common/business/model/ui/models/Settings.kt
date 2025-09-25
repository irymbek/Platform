package kz.rymbek.platform.common.business.model.ui.models

import androidx.compose.ui.graphics.vector.ImageVector
import kz.rymbek.platform.common.base.feature.architecture.IEvent

data class Settings(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val navigationEvent: IEvent
)