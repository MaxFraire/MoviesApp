package com.maxfraire.movies.data.remote.paging

import androidx.paging.PagingSource
import com.maxfraire.movies.data.remote.api.MoviesAPI
import com.maxfraire.movies.data.remote.models.MovieDTO
import com.maxfraire.movies.data.remote.models.MovieListType
import com.maxfraire.movies.util.orDefault
import timber.log.Timber
import javax.inject.Inject

class MoviePagingSourceFactory @Inject constructor(
    val api: MoviesAPI
) {
    fun create(movieListType: MovieListType): MoviePagingSource {
        return MoviePagingSource(api = api, movieListType = movieListType)
    }

    fun create(query: String): MoviePagingSource {
        return MoviePagingSource(api = api, query = query)
    }
}

class MoviePagingSource(
    val api: MoviesAPI,
    val movieListType: MovieListType? = null,
    val query: String? = null
) : PagingSource<Int, MovieDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
        return try {
            val page = params.key ?: DEFAULT_PAGE
            val response = if (movieListType != null) api.fetchMovies(movieListType.type, page)
            else api.searchMovie(query.orEmpty(), page)

            LoadResult.Page(
                data = response.body()?.results.orEmpty(),
                prevKey = if (page == DEFAULT_PAGE) null else page.dec(),
                nextKey = if (response.body()?.totalPages.orDefault(0) > page) response.body()?.page?.inc() else null
            )
        } catch (e: Exception) {
            Timber.e("Error loading page. ${e.message}")
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val DEFAULT_PAGE = 1
    }
}