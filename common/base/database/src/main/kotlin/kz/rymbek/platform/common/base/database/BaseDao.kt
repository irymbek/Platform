package kz.rymbek.platform.common.base.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.room.Upsert
import androidx.sqlite.db.SupportSQLiteQuery

interface BaseDao<Entity> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: Entity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entities: List<Entity>): LongArray

    @Update
    fun update(entity: Entity)

    @Update
    fun update(entities: List<Entity>)

    @Upsert
    suspend fun upsert(entity: Entity): Long

    @Upsert
    suspend fun upsert(entities: List<Entity>): LongArray

    @Delete
    fun delete(entity: Entity)

    @Delete
    fun delete(entities: List<Entity>)

    @RawQuery
    fun getItem(
        query: SupportSQLiteQuery,
    ): Entity
}