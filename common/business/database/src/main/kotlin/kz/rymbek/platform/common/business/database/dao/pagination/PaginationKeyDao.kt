package kz.rymbek.platform.common.business.database.dao.pagination

@Dao
interface PaginationKeyDao: BaseDao<PaginationKeyEntity> {
    @Query("SELECT currentKey FROM pagination_keys WHERE type = :type")
    suspend fun getKey(type: String): Int?

    @Query("SELECT created FROM pagination_keys WHERE type = :type ORDER BY created DESC LIMIT 1")
    suspend fun getCreationTime(type: String): Long?

    @Query("DELETE FROM pagination_keys WHERE type = :type")
    suspend fun clearAll(type: String)
}