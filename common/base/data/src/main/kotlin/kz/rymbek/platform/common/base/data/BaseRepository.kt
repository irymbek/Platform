package kz.rymbek.platform.common.base.data

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kz.rymbek.platform.common.base.pagination.BaseRemoteMediator
import kz.rymbek.platform.common.base.pagination.PaginationKeyStorage
import kz.rymbek.platform.common.base.pagination.PagingUtils.createPagingConfig
import kz.rymbek.platform.common.base.pagination.network.BaseNetworkPagingSource
import kz.rymbek.platform.common.core.architecture.DataResult
import kz.rymbek.platform.common.core.architecture.NetworkResult

abstract class BaseRepository {
    protected fun <Remote : Any, Ui : Any> getPagedRemote(
        fetchFromNetwork: suspend (page: Int, pageSize: Int) -> List<Remote>,
        mapToUi: suspend (Remote) -> Ui,
    ): Flow<PagingData<Ui>> = Pager(
        config = createPagingConfig(),
        pagingSourceFactory = {
            BaseNetworkPagingSource(fetchFromNetwork)
        },
    ).flow.map { pagingData ->
        pagingData.map { mapToUi(it) }
    }

    protected fun <Entity : Any, Ui : Any> getPagedData(
        pagingSourceFactory: () -> PagingSource<Int, Entity>,
        mapToUi: suspend (Entity) -> Ui,
    ): Flow<PagingData<Ui>> = Pager(
        config = createPagingConfig(),
        pagingSourceFactory = pagingSourceFactory,
    ).flow.map { it.map(mapToUi) }

    protected fun <Entity : Any, Remote : Any, Ui : Any> getPagedCombined(
        paginationType: String,
        fetchFromNetwork: suspend (Int, Int) -> List<Remote>,
        pagingSourceFactory: () -> PagingSource<Int, Entity>,
        keyStorage: PaginationKeyStorage,
        mapToUi: suspend (Entity) -> Ui,
        saveData: suspend (List<Remote>) -> Unit,
        deleteData: suspend () -> Unit,
        forceRefresh: Boolean = false,
    ): Flow<PagingData<Ui>> = Pager(
        config = createPagingConfig(),
        remoteMediator = BaseRemoteMediator(
            paginationType = paginationType,
            keyStorage = keyStorage,
            fetchFromNetwork = fetchFromNetwork,
            saveData = saveData,
            deleteData = deleteData,
            forceRefresh = forceRefresh,
        ),
        pagingSourceFactory = pagingSourceFactory,
    ).flow.map { it.map(mapToUi) }

    /*protected fun <Local, Remote> syncFlow(
        localFlow: Flow<Local>,
        remoteFlow: Flow<NetworkResult<Remote>>,
        onSuccess: suspend (Remote) -> Unit
    ): Flow<DataResult<Local>> = combine(
        localFlow,
        remoteFlow
    ) { local, networkStatus ->
        when (networkStatus) {
            is NetworkResult.Loading -> DataResult.Loading(data = local)
            is NetworkResult.Success -> {
                onSuccess(networkStatus.data)
                DataResult.Success(data = local)
            }
            is NetworkResult.Error -> DataResult.Error(
                exception = networkStatus.exception,
                data = local  // данные не исчезают!
            )
        }
    }*/

    protected fun <Local, Remote> syncFlow(
        localFlow: Flow<Local>,
        remoteFlow: Flow<NetworkResult<Remote>>,
        onSuccess: suspend (Remote) -> Unit
    ): Flow<DataResult<Local>> = combine(
        localFlow,
        remoteFlow.onEach {
            if (it is NetworkResult.Success) onSuccess(it.data)
        }
    ) { local, network ->
        when (network) {
            is NetworkResult.Loading -> DataResult.Loading(data = local)
            is NetworkResult.Success -> DataResult.Success(data = local)
            is NetworkResult.Error -> DataResult.Error(network.exception, data = local)
        }
    }.distinctUntilChanged()
}

