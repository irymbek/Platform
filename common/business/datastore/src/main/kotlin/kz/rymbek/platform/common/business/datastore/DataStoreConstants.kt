package kz.rymbek.platform.common.business.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

internal object DataStoreConstants {
    const val APP_DATA_STORE_NAME = "app_datastore"
    val THEME_KEY = stringPreferencesKey("theme_key")
    val MODE_KEY = stringPreferencesKey("mode_key")
}