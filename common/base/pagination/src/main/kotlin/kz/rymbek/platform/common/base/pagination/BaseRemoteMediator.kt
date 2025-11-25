package kz.rymbek.platform.common.base.pagination

import android.util.Log
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import java.io.IOException
import java.util.concurrent.TimeUnit

class BaseRemoteMediator<Response : Any, Local : Any>(
    private val paginationType: String,
    private val keyStorage: PaginationKeyStorage,
    private val fetchFromNetwork: suspend (Int, Int) -> List<Response>,
    private val saveData: suspend (List<Response>) -> Unit,
    private val deleteData: suspend () -> Unit = {},
) : RemoteMediator<Int, Local>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Local>
    ): MediatorResult {
        return try {
            val page: Int =
                getPage(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

            val response = fetchFromNetwork(page, state.config.pageSize)

            val nextKey = if (response.isNotEmpty()) page + 1 else null

            if (response.isNotEmpty()) page + 1 else null

            keyStorage.upsert(paginationType, nextKey = nextKey)

            saveData(response)

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (exception: IOException) {
            Log.d("BaseRemoteMediator", "$exception")
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getPage(loadType: LoadType): Int? {
        return when (loadType) {
            LoadType.REFRESH -> {
                keyStorage.clear(paginationType)
                deleteData()
                1
            }

            LoadType.APPEND -> keyStorage.getKey(paginationType)
            LoadType.PREPEND -> null
        }
    }

    override suspend fun initialize(): InitializeAction {
        return if (System.currentTimeMillis() - (keyStorage.getCreationTime(paginationType)
                ?: 0) < TimeUnit.MILLISECONDS.convert(2, TimeUnit.HOURS)
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }
}