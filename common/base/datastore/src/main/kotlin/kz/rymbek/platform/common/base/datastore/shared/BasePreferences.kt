package kz.rymbek.platform.common.base.datastore.shared

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

abstract class BasePreferences(
    context: Context,
    preferenceName: String,
) : BasePreferencesInterface {
    override val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    override fun putString(
        key: String,
        value: String,
    ) {
        sharedPreferences.edit { putString(key, value) }
    }

    override fun getString(
        key: String,
        defaultValue: String,
    ): String = sharedPreferences.getString(key, defaultValue) ?: defaultValue

    override fun putLong(
        key: String,
        value: Long,
    ) {
        sharedPreferences.edit { putLong(key, value) }
    }

    override fun getLong(
        key: String,
        defaultValue: Long,
    ): Long = sharedPreferences.getLong(key, defaultValue)

    override fun clear() {
        sharedPreferences.edit { clear() }
    }
}
