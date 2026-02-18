package kz.rymbek.platform.common.base.data

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.rymbek.platform.common.base.pagination.BaseRemoteMediator
import kz.rymbek.platform.common.base.pagination.PaginationKeyStorage
import kz.rymbek.platform.common.base.pagination.PagingUtils.createPagingConfig

abstract class BaseRepository {
    protected fun <Entity : Any, Ui : Any> getPagedData(
        pagingSourceFactory: () -> PagingSource<Int, Entity>,
        mapToUi: suspend (Entity) -> Ui
    ): Flow<PagingData<Ui>> {
        return Pager(
            config = createPagingConfig(),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { it.map(mapToUi) }
    }

    protected fun <Entity : Any, Remote : Any, Ui : Any> getPagedCombined(
        paginationType: String,
        fetchFromNetwork: suspend (Int, Int) -> List<Remote>,
        pagingSourceFactory: () -> PagingSource<Int, Entity>,
        keyStorage: PaginationKeyStorage,
        mapToUi: suspend (Entity) -> Ui,
        saveData: suspend (List<Remote>) -> Unit,
        deleteData: suspend () -> Unit,
        forceRefresh: Boolean = false
    ): Flow<PagingData<Ui>> {
        return Pager(
            config = createPagingConfig(),
            remoteMediator = BaseRemoteMediator(
                paginationType = paginationType,
                keyStorage = keyStorage,
                fetchFromNetwork = fetchFromNetwork,
                saveData = saveData,
                deleteData = deleteData,
                forceRefresh = forceRefresh,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { it.map(mapToUi) }
    }
}