package com.example.homework18.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework18.dataclass.Data
import com.example.homework18.service.ApiService
import retrofit2.HttpException
import java.io.IOException

class PagingSource(private val apiService: ApiService) : PagingSource<Int, Data>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Data> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getUsersInfo(page)
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = if (page < response.totalPages) page + 1 else null
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
