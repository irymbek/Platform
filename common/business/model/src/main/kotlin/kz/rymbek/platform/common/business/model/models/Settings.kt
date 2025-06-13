package kz.rymbek.platform.common.business.model.models

import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.base.model.interfaces.Identifiable

data class Settings(
    override val id: Long,
    val title: String,
    val description: String,
    val icon: String,
    val navigationEvent: IEvent
): Identifiable