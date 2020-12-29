package com.maxfraire.movies.ui.models

import com.maxfraire.movies.data.remote.models.GenreDTO
import com.maxfraire.movies.data.remote.models.MovieDTO
import com.maxfraire.movies.data.remote.models.MovieListType
import com.maxfraire.movies.util.Constants
import javax.inject.Inject

class MoviesUIDataMapper @Inject constructor() {

    fun convert(movieDto: MovieDTO): MovieUI =
        MovieUI(
            id = movieDto.id ?: 0,
            title = movieDto.title.orEmpty(),
            originalTitle = movieDto.originalTitle.orEmpty(),
//            genres = movieDto.genres.map { convert(it) },
            releaseDate = movieDto.releaseDate.orEmpty(),
            voteAverage = movieDto.voteAverage.orEmpty(),
            overview = movieDto.overview.orEmpty(),
            runtime = movieDto.runtime.orEmpty(),
            backdropPath = movieDto.backdropPath.orEmpty(),
            posterPath = Constants.BASE_IMAGE_URL.plus(movieDto.posterPath.orEmpty())
        )

    fun convert(movieListTypeUI: MovieListTypeUI): MovieListType =
        when (movieListTypeUI) {
            MovieListTypeUI.Popular -> MovieListType.Popular
            MovieListTypeUI.Upcoming -> MovieListType.Upcoming
            MovieListTypeUI.TopRated -> MovieListType.TopRated
            MovieListTypeUI.InTheaters -> MovieListType.InTheaters
        }

    fun convert(movieListType: MovieListType): MovieListTypeUI =
        when (movieListType) {
            MovieListType.Popular -> MovieListTypeUI.Popular
            MovieListType.Upcoming -> MovieListTypeUI.Upcoming
            MovieListType.TopRated -> MovieListTypeUI.TopRated
            MovieListType.InTheaters -> MovieListTypeUI.InTheaters
        }

    private fun convert(genre: GenreDTO): GenreUI =
        GenreUI(genre.id, genre.name)
}