package kz.rymbek.platform.common.feature.settings.theme.viewmodel.contract

import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.enums.design.ModeConfig

sealed interface SettingsThemeEvent : IEvent {
    sealed interface Action : SettingsThemeEvent, IEvent.Action {
        data class UpdateThemeBrand(val appThemeBrand: AppThemeBrand) : Action
        data class UpdateModeConfig(val modeConfig: ModeConfig) : Action
    }
}