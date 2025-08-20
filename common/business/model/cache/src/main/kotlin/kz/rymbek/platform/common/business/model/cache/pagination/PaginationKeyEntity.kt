package kz.rymbek.platform.common.business.model.cache.pagination

import androidx.room.Entity

@Entity(
    tableName = "pagination_keys",
    primaryKeys = ["type"]
)
data class PaginationKeyEntity(
    val type: String,
    val nextKey: Int?,
    val created: Long = System.currentTimeMillis(),
)