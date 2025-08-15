package kz.rymbek.platform.common.base.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class BaseDataStore(
    context: Context,
    dataStoreName: String
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = dataStoreName
    )

    val dataStore = context.dataStore

    inline fun <reified T : Any> getValueOrNull(
        key: Preferences.Key<T>,
    ): Flow<T?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun <T : Enum<T>> saveValue(key: Preferences.Key<String>, value: T) {
        dataStore.edit { preferences -> preferences[key] = value.name }
    }

    inline fun <reified T : Enum<T>> getValue(key: Preferences.Key<String>, default: T): Flow<T> {
        return dataStore.data.map { preferences ->
            enumValueOf(preferences[key] ?: default.name)
        }
    }

    suspend fun <T : Any> saveValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

    inline fun <reified T : Any> getValue(key: Preferences.Key<T>, default: T): Flow<T> {
        return dataStore.data.map { preferences -> preferences[key] ?: default }
    }

    suspend fun removeValue(key: Preferences.Key<*>) {
        dataStore.edit { preferences -> preferences.remove(key) }
    }
}