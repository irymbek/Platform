package kz.rymbek.platform.common.business.model.models

import kz.rymbek.platform.common.business.model.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.enums.design.ModeConfig

data class AppData(
    val appThemeBrand: AppThemeBrand = AppThemeBrand.DEFAULT,
    val modeConfig: ModeConfig = ModeConfig.FOLLOW_SYSTEM,
)