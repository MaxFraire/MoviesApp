package com.maxfraire.movies.data.remote.api

import com.maxfraire.movies.data.remote.models.MoviesListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

//    @GET("/3/movie/{$PATH_MOVIE_LIST_TYPE}?api_key=59901e3dfa59a026c885ca9e783528a8")
    @GET("/3/movie/{$PATH_MOVIE_LIST_TYPE}")
    suspend fun fetchMovies(
        @Path(PATH_MOVIE_LIST_TYPE) listType: String,
        @Query(QUERY_PAGE) page: Int
    ): Response<MoviesListDTO>

    companion object {
        const val PATH_MOVIE_LIST_TYPE = "movie_list_type"
        const val QUERY_PAGE = "page"
        const val QUERY_PAGE_SIZE = "page_size"
    }
}
