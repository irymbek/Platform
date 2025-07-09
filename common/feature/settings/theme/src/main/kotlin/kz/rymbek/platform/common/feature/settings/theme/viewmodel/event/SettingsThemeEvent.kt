package kz.rymbek.platform.common.feature.settings.theme.viewmodel.event

import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.business.model.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.enums.design.ModeConfig

sealed class SettingsThemeEvent : IEvent {
    sealed class Navigation: SettingsThemeEvent() {
        data object Back: Navigation()
    }
    sealed class Action : SettingsThemeEvent() {
        data class UpdateThemeBrand(val appThemeBrand: AppThemeBrand) : Action()
        data class UpdateModeConfig(val modeConfig: ModeConfig) : Action()
    }
}