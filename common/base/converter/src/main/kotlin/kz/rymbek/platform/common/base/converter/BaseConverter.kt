package kz.rymbek.platform.common.base.converter

import androidx.room.TypeConverter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.json.Json

abstract class BaseConverter<T>(
    private val serializer: KSerializer<T>,
) {
    @TypeConverter
    fun toString(data: T): String = Json.encodeToString(serializer, data)

    @TypeConverter
    fun fromString(data: String): T? = Json.decodeFromString(serializer, data)

    @TypeConverter
    fun toList(data: String): List<T> = Json.decodeFromString(ListSerializer(serializer), data)

    @TypeConverter
    fun listToString(data: List<T>): String = Json.encodeToString(ListSerializer(serializer), data)

    @TypeConverter
    fun toSet(data: String): Set<T> = Json.decodeFromString(SetSerializer(serializer), data)

    @TypeConverter
    fun setToString(data: Set<T>): String = Json.encodeToString(SetSerializer(serializer), data)
}
