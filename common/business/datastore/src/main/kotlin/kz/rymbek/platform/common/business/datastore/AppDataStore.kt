package kz.rymbek.platform.common.business.datastore

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kz.rymbek.platform.common.base.datastore.BaseDataStore
import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.enums.design.ModeConfig
import kz.rymbek.platform.common.business.model.ui.models.AppData
import org.koin.core.annotation.Single

@Single
internal class AppDataStore(
    context: Context,
) : BaseDataStore(context, DataStoreConstants.APP_DATA_STORE_NAME),
    AppDataStoreInterface {
    override fun getSettings(): Flow<AppData> {
        return combine(
            getModeConfig(),
            getThemeBrand(),
        ) { modeConfig, themeBrand ->
            AppData(
                appThemeBrand = themeBrand,
                modeConfig = modeConfig,
            )
        }
    }


    override fun getThemeBrand(): Flow<AppThemeBrand> {
        return getValue(
            DataStoreConstants.THEME_KEY,
            AppThemeBrand.DEFAULT
        )
    }

    override suspend fun setThemeBrand(appThemeBrand: AppThemeBrand) {
       saveValue(DataStoreConstants.THEME_KEY, appThemeBrand)
    }

    override fun getModeConfig(): Flow<ModeConfig> {
        return getValue(
            DataStoreConstants.MODE_KEY,
            ModeConfig.FOLLOW_SYSTEM
        )
    }

    override suspend fun setModeConfig(modeConfig: ModeConfig) {
        saveValue(DataStoreConstants.MODE_KEY, modeConfig)
    }
}