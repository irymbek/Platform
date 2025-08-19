package kz.rymbek.platform.common.base.pagination

import androidx.paging.PagingConfig

object PagingUtils {
    fun createPagingConfig(
        pageSize: Int = 10,
        prefetchDistance: Int = 4,
        initialLoadSize: Int = pageSize,
        maxSize: Int = pageSize * 3,
    ): PagingConfig {
        return PagingConfig(
            pageSize = pageSize,
            prefetchDistance = prefetchDistance,
            enablePlaceholders = true,
            initialLoadSize = initialLoadSize,
            maxSize = maxSize
        )
    }
}
