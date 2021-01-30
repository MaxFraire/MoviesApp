package com.maxfraire.movies.data.remote.api

import com.maxfraire.movies.data.remote.models.MovieDTO
import com.maxfraire.movies.data.remote.models.MoviesListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    @GET("/3/movie/{$PATH_MOVIE_LIST_TYPE}")
    suspend fun fetchMovies(
        @Path(PATH_MOVIE_LIST_TYPE) listType: String,
        @Query(QUERY_PAGE) page: Int
    ): Response<MoviesListDTO>

    @GET("/3/movie/{$PATH_MOVIE_ID}?$QUERY_CREDITS")
    suspend fun fetchMovieById(
        @Path(PATH_MOVIE_ID) movieId: Int
    ): Response<MovieDTO>

    @GET("/3/search/movie")
    suspend fun searchMovie(
        @Query(QUERY_SEARCH) query: String,
        @Query(QUERY_PAGE) page: Int
    ): Response<MoviesListDTO>

    companion object {
        const val PATH_MOVIE_LIST_TYPE = "movie_list_type"
        const val PATH_MOVIE_ID = "movie_id"
        const val QUERY_PAGE = "page"
        const val QUERY_CREDITS = "append_to_response=credits"
        const val QUERY_SEARCH = "query"
    }
}
