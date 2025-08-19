package kz.rymbek.platform.common.business.data.app.interfaces

import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.enums.design.ModeConfig
import kz.rymbek.platform.common.business.model.ui.models.AppData

interface AppRepositoryInterface {
    fun getSettings(): Flow<AppData>

    fun getThemeBrand(): Flow<AppThemeBrand>

    fun getModeConfig(): Flow<ModeConfig>

    suspend fun setThemeBrand(appThemeBrand: AppThemeBrand)

    suspend fun setModeConfig(modeConfig: ModeConfig)
}