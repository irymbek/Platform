package kz.rymbek.platform.common.feature.settings.theme.viewmodel.state

import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.models.AppData

data class SettingsThemeUiState(
    val appData: AppData = AppData(),
    val appThemeBrand: List<AppThemeBrand> = AppThemeBrand.entries,
)