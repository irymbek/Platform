package kz.rymbek.platform.common.base.pagination.network

import androidx.paging.PagingSource
import androidx.paging.PagingState

class BaseNetworkPagingSource<Remote : Any>(
    private val fetchFromNetwork: suspend (page: Int, pageSize: Int) -> List<Remote>
) : PagingSource<Int, Remote>() {

    override fun getRefreshKey(state: PagingState<Int, Remote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Remote> {
        val page = params.key ?: 1
        return try {
            val response = fetchFromNetwork(page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}