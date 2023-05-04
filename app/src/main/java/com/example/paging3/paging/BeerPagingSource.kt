package com.example.paging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.BeerApi
import com.example.paging3.models.Beer

class BeerPagingSource(private val beerApi: BeerApi) : PagingSource<Int, Beer>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val position = params.key ?: 1
            val response = beerApi.getBeers(position)

            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.size) null else position + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    // anchorPosition -> Most recently accessed index in the list
    // closestPageToPosition -> fetch the loaded page that is closest to the last accessed index in the list

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}