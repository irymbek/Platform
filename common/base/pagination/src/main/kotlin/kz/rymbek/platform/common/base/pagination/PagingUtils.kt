package kz.rymbek.platform.common.base.pagination

import androidx.paging.PagingConfig

object PagingUtils {
    fun createPagingConfig(
        pageSize: Int = 10,
        prefetchDistance: Int = pageSize,
        enablePlaceHolders: Boolean = false,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE
    ): PagingConfig {
        return PagingConfig(
            pageSize = pageSize,
            prefetchDistance = prefetchDistance,
            enablePlaceholders = enablePlaceHolders,
            initialLoadSize = initialLoadSize,
            maxSize = maxSize,
            jumpThreshold = jumpThreshold,
        )
    }
}
