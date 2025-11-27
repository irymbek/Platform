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
    suspend fun insert(entity: Entity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entities: List<Entity>): LongArray

    @Update
    suspend fun update(entity: Entity)

    @Update
    suspend fun update(entities: List<Entity>)

    @Upsert
    suspend fun upsert(entity: Entity): Long

    @Upsert
    suspend fun upsert(entities: List<Entity>): LongArray

    @Delete
    suspend fun delete(entity: Entity)

    @Delete
    suspend fun delete(entities: List<Entity>)

    @RawQuery
    suspend fun getItem(
        query: SupportSQLiteQuery,
    ): Entity
}