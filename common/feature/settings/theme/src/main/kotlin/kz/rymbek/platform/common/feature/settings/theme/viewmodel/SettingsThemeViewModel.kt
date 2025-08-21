package kz.rymbek.platform.common.feature.settings.theme.viewmodel

import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.base.feature.viewmodel.OrbitViewModel
import kz.rymbek.platform.common.business.data.app.interfaces.AppRepositoryInterface
import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.enums.design.ModeConfig
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.event.SettingsThemeEvent
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.state.SettingsThemeUiState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsThemeViewModel(
    private val appRepositoryInterface: AppRepositoryInterface,
) : OrbitViewModel<SettingsThemeUiState, SettingsThemeEvent.Navigation>(SettingsThemeUiState()) {
    override fun handleAction(
        event: IEvent.Action,
    ) {
        when (event) {
            is SettingsThemeEvent.Action.UpdateThemeBrand -> {
                updateThemeBrand(appThemeBrand = event.appThemeBrand)
            }
            is SettingsThemeEvent.Action.UpdateModeConfig -> {
                updateModeConfig(modeConfig = event.modeConfig)
            }
        }
    }

    init {
        getSettings()
    }

    private fun getSettings() = intent {
        appRepositoryInterface
            .getSettings()
            .collect { settings ->
                reduce { state.copy(appData = settings) }
            }
    }

    private fun updateThemeBrand(appThemeBrand: AppThemeBrand) = intent {
        appRepositoryInterface.setThemeBrand(appThemeBrand)
    }

    private fun updateModeConfig(modeConfig: ModeConfig) = intent {
        appRepositoryInterface.setModeConfig(modeConfig)
    }
}