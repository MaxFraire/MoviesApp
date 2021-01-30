package com.maxfraire.movies.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxfraire.movies.data.common.DataMapper
import com.maxfraire.movies.data.common.NetworkBoundResource
import com.maxfraire.movies.data.common.Resource
import com.maxfraire.movies.data.common.getResource
import com.maxfraire.movies.data.local.dao.CastDao
import com.maxfraire.movies.data.local.dao.MoviesDao
import com.maxfraire.movies.data.local.entities.MovieEntity
import com.maxfraire.movies.data.local.entities.MovieWithCastEntity
import com.maxfraire.movies.data.remote.api.MoviesAPI
import com.maxfraire.movies.data.remote.models.MovieDTO
import com.maxfraire.movies.data.remote.models.MovieListType
import com.maxfraire.movies.data.remote.models.MoviesListDTO
import com.maxfraire.movies.data.remote.paging.MoviePagingSourceFactory
import com.maxfraire.movies.di.ApplicationScope
import com.maxfraire.movies.util.orDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@ApplicationScope
class MoviesRepository @Inject constructor(
    private val api: MoviesAPI,
    private val moviesDao: MoviesDao,
    private val castDao: CastDao,
    private val mapper: DataMapper,
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

    fun searchMovie(
        query: String,
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): Flow<PagingData<MovieDTO>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { moviesPagingSourceFactory.create(query) }
        ).flow

    fun getMovieById(movieId: Int): Flow<Resource<MovieWithCastEntity?>> =
        object : NetworkBoundResource<MovieWithCastEntity, MovieDTO>() {
            override suspend fun saveNetworkResult(item: MovieDTO?) {
                item?.let {
                    moviesDao.insertMovie(mapper.convert(item))
                    castDao.insertCast(
                        item.credits?.cast?.map { cast ->
                            mapper.convert(cast, it.id.orDefault(0))
                        }.orEmpty()
                    )
                     Timber.d("Movie inserted into the database")
                 }
             }

             override fun shouldFetch(data: MovieWithCastEntity?): Boolean =
                 data == null

             override suspend fun loadFromDb(): Flow<MovieWithCastEntity?> =
                 moviesDao.getMovieWithCast(movieId)

             override suspend fun fetchFromNetwork(): Response<MovieDTO> =
                 api.fetchMovieById(movieId)

         }.asFlow()

    suspend fun setFavorite(movieId: Int, isFavorite: Boolean) {
        moviesDao.favoriteMovie(movieId, isFavorite)
    }

    fun getFavorites(): Flow<List<MovieEntity>> {
        return moviesDao.getFavorites()
            .flowOn(Dispatchers.IO)
    }

    private fun getDefaultPageConfig(): PagingConfig =
        PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}