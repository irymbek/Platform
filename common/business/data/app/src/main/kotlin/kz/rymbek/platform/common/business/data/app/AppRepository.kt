package kz.rymbek.platform.common.business.data.app

import kotlinx.coroutines.flow.Flow
import kz.rymbek.platform.common.business.data.app.interfaces.AppRepositoryInterface
import kz.rymbek.platform.common.business.datastore.AppDataStoreInterface
import kz.rymbek.platform.common.business.model.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.enums.design.ModeConfig
import kz.rymbek.platform.common.business.model.models.AppData
import org.koin.core.annotation.Single

@Single
internal class AppRepository(
    private val appDataStoreInterface: AppDataStoreInterface,
) : AppRepositoryInterface {
    override suspend fun getSettings(): Flow<AppData> {
        return appDataStoreInterface.getSettings()
    }

    override suspend fun setThemeBrand(appThemeBrand: AppThemeBrand) {
        appDataStoreInterface.setThemeBrand(appThemeBrand = appThemeBrand)
    }

    override suspend fun setModeConfig(modeConfig: ModeConfig) {
        appDataStoreInterface.setModeConfig(modeConfig = modeConfig)
    }

    override suspend fun getThemeBrand(): Flow<AppThemeBrand> {
        return appDataStoreInterface.getThemeBrand()
    }

    override suspend fun getModeConfig(): Flow<ModeConfig> {
        return appDataStoreInterface.getModeConfig()
    }
}