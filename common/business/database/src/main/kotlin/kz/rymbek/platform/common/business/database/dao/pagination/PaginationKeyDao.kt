package kz.rymbek.platform.common.business.database.dao.pagination

import androidx.room.Dao
import androidx.room.Query
import kz.rymbek.platform.common.base.database.BaseDao
import kz.rymbek.platform.common.business.model.cache.pagination.PaginationKeyEntity

@Dao
interface PaginationKeyDao: BaseDao<PaginationKeyEntity> {
    @Query("SELECT nextKey FROM pagination_keys WHERE type = :type")
    suspend fun getKey(type: String): Int?

    @Query("SELECT created FROM pagination_keys WHERE type = :type ORDER BY created DESC LIMIT 1")
    suspend fun getCreationTime(type: String): Long?

    @Query("DELETE FROM pagination_keys WHERE type = :type")
    suspend fun clearAll(type: String)
}