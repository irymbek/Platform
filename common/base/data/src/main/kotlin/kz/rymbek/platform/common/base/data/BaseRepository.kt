package kz.rymbek.platform.common.base.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class BaseRepository {
    /*protected suspend fun <T> runInTransaction(
        db: RoomDatabase,
        block: suspend () -> T
    ): T {
        return db.withTransaction {
            block()
        }
    }*/


    protected fun <Entity: Any , Ui: Any> getPagedData(
        pageSize: Int = 10,
        prefetchDistance: Int = 5,
        initialLoadSize: Int = 20,
        maxSize: Int = 30,
        pagingSourceFactory: () -> PagingSource<Int, Entity>,
        mapToUi: (Entity) -> Ui
    ): Flow<PagingData<Ui>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = prefetchDistance,
                enablePlaceholders = true,
                initialLoadSize = initialLoadSize,
                maxSize = maxSize
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map {
                item -> mapToUi(item)
            }
        }
    }
}