package kz.rymbek.platform.common.business.data.app.interfaces

import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.business.model.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.enums.design.ModeConfig
import kz.rymbek.platform.common.business.model.models.AppData

interface AppRepositoryInterface {
    suspend fun getSettings(): Flow<AppData>

    suspend fun setThemeBrand(appThemeBrand: AppThemeBrand)

    suspend fun setModeConfig(modeConfig: ModeConfig)

    suspend fun getThemeBrand(): Flow<AppThemeBrand>

    suspend fun getModeConfig(): Flow<ModeConfig>
}