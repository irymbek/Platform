package kz.rymbek.platform.common.business.datastore

import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.business.model.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.enums.design.ModeConfig
import kz.rymbek.platform.common.business.model.models.AppData

interface AppDataStoreInterface {
    suspend fun getSettings(): Flow<AppData>

    fun getThemeBrand(): Flow<AppThemeBrand>
    suspend fun setThemeBrand(appThemeBrand: AppThemeBrand)

    fun getModeConfig(): Flow<ModeConfig>
    suspend fun setModeConfig(modeConfig: ModeConfig)
}