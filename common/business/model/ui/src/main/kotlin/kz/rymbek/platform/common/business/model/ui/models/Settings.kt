package kz.rymbek.platform.common.business.model.ui.models

import kz.rymbek.platform.common.base.feature.architecture.IEvent

data class Settings(
    val title: String,
    val description: String,
    val icon: String,
    val navigationEvent: IEvent
)