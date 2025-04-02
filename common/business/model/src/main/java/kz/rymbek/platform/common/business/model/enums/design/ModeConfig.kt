package kz.rymbek.platform.common.business.model.enums.design

import kz.rymbek.platform.common.core.architecture.interfaces.Filterable

enum class ModeConfig(
    override val label: String,
) : Filterable {
    FOLLOW_SYSTEM(
        label = "Системная",
    ),
    LIGHT(
        label = "Светлая",
    ),
    DARK(
        label = "Тёмная",
    ),
}