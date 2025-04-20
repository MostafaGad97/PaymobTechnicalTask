package com.example.paymobtechnicaltask.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paymobtechnicaltask.data.remote.dto.MovieDto
import com.example.paymobtechnicaltask.data.utils.handlePagingError

class MoviesPagingSource(
    private val api: MoviesService
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val page = params.key ?: 1
        return try {
            val response = api.getPopularMovies(page)
            LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results?.isEmpty() == true) null else page + 1
            )
        } catch (e: Exception) {
            return handlePagingError(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}