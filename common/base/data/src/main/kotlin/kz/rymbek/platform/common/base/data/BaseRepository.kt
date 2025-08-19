package kz.rymbek.platform.common.base.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.rymbek.platform.common.base.pagination.BaseRemoteMediator
import kz.rymbek.platform.common.base.pagination.PaginationKeyStorage
import kz.rymbek.platform.common.base.pagination.PagingUtils

abstract class BaseRepository {
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

    protected fun <Local : Any, Remote : Any, Ui: Any> getPagedCombined(
        paginationType: String,
        fetchFromNetwork: suspend (Int, Int) -> List<Remote>,
        pagingSourceFactory: () -> PagingSource<Int, Local>,
        keyStorage: PaginationKeyStorage,
        mapToUi: (Local) -> Ui,
        saveData: suspend (List<Remote>) -> Unit,
        deleteData: suspend () -> Unit,
    ): Flow<PagingData<Ui>> {
        return Pager(
            config = PagingUtils.createPagingConfig(),
            remoteMediator = BaseRemoteMediator(
                paginationType = paginationType,
                keyStorage = keyStorage,
                fetchFromNetwork = fetchFromNetwork,
                saveData = saveData,
                deleteData = deleteData,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { it.map(mapToUi) }
    }
}