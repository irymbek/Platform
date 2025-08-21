package kz.rymbek.platform.common.feature.settings.theme.viewmodel.contract

import kz.rymbek.platform.common.base.feature.architecture.IEvent

interface SettingsThemeSideEffect : IEvent {
    sealed interface Navigation : SettingsThemeSideEffect, IEvent.Navigation {
        data object Back: Navigation
    }
}