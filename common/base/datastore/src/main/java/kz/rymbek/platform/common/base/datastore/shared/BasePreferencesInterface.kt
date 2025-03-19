package kz.rymbek.platform.common.base.datastore.shared

import android.content.SharedPreferences

interface BasePreferencesInterface {
    val sharedPreferences: SharedPreferences

    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String = ""): String
    fun putLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long = -1L): Long
    fun clear()
}