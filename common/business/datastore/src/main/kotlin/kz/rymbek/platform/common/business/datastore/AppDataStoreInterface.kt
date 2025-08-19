package kz.rymbek.platform.common.business.datastore

import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.enums.design.ModeConfig
import kz.rymbek.platform.common.business.model.ui.models.AppData

interface AppDataStoreInterface {
    fun getSettings(): Flow<AppData>

    fun getThemeBrand(): Flow<AppThemeBrand>
    suspend fun setThemeBrand(appThemeBrand: AppThemeBrand)

    fun getModeConfig(): Flow<ModeConfig>
    suspend fun setModeConfig(modeConfig: ModeConfig)
}