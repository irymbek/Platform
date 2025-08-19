package kz.aniliberty.project.platform.common.base.pagination

interface PaginationKeyStorage {
    suspend fun getKey(type: String): Int?
    suspend fun getCreationTime(type: String): Long?
    suspend fun clear(type: String)
    suspend fun upsert(type: String, currentKey: Int?)
}