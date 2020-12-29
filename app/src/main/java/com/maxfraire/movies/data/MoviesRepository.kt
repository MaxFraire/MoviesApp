package com.maxfraire.movies.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxfraire.movies.data.common.Resource
import com.maxfraire.movies.data.common.getResource
import com.maxfraire.movies.data.remote.api.MoviesAPI
import com.maxfraire.movies.data.remote.models.MovieDTO
import com.maxfraire.movies.data.remote.models.MovieListType
import com.maxfraire.movies.data.remote.models.MoviesListDTO
import com.maxfraire.movies.data.remote.paging.MoviePagingSourceFactory
import com.maxfraire.movies.di.ApplicationScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ApplicationScope
class MoviesRepository @Inject constructor(
    private val api: MoviesAPI,
    private val moviesPagingSourceFactory: MoviePagingSourceFactory
) {

    suspend fun getMovies(movieListType: MovieListType, page: Int): Flow<Resource<MoviesListDTO?>> =
        flow {
            emit(Resource.Loading(null))
            val response = getResource {
                api.fetchMovies(movieListType.type, page)
            }
            emit(response)
        }.flowOn(Dispatchers.IO)

    fun getPaginatedMovies(
        movieListType: MovieListType,
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): Flow<PagingData<MovieDTO>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { moviesPagingSourceFactory.create(movieListType) }
        ).flow

    private fun getDefaultPageConfig(): PagingConfig =
        PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)


    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}