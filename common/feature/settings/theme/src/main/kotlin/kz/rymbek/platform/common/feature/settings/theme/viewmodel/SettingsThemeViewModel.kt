package kz.rymbek.platform.common.feature.settings.theme.viewmodel

import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.base.feature.viewmodel.BaseViewModel
import kz.rymbek.platform.common.base.navigation.core.BaseNavigatorInterface
import kz.rymbek.platform.common.business.data.app.interfaces.AppRepositoryInterface
import kz.rymbek.platform.common.business.model.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.enums.design.ModeConfig
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.event.SettingsThemeEvent
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.state.SettingsThemeUiState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsThemeViewModel(
    private val baseNavigatorInterface: BaseNavigatorInterface,
    private val appRepositoryInterface: AppRepositoryInterface,
) : BaseViewModel<SettingsThemeUiState>(SettingsThemeUiState()) {
    override fun handleNavigation(
        event: IEvent.Navigation,
    ) {
        when (event) {
            is SettingsThemeEvent.Navigation.Back -> {
                baseNavigatorInterface.navigateBack()
            }
        }
    }

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

    private fun getSettings() {
        viewModelScopeCustom {
            appRepositoryInterface
                .getSettings()
                .collect {
                    updateState {
                        copy(
                            appData = it
                        )
                    }
                }
        }
    }

    private fun updateThemeBrand(appThemeBrand: AppThemeBrand) {
        viewModelScopeCustom {
            appRepositoryInterface.setThemeBrand(appThemeBrand)
        }
    }

    private fun updateModeConfig(modeConfig: ModeConfig) {
        viewModelScopeCustom {
            appRepositoryInterface.setModeConfig(modeConfig)
        }
    }
}