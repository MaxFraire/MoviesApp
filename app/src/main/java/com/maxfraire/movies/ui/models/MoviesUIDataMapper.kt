package com.maxfraire.movies.ui.models

import android.annotation.SuppressLint
import com.maxfraire.movies.data.local.entities.CastEntity
import com.maxfraire.movies.data.local.entities.MovieEntity
import com.maxfraire.movies.data.local.entities.MovieWithCastEntity
import com.maxfraire.movies.data.remote.models.GenreDTO
import com.maxfraire.movies.data.remote.models.MovieDTO
import com.maxfraire.movies.data.remote.models.MovieListType
import com.maxfraire.movies.util.*
import javax.inject.Inject

class MoviesUIDataMapper @Inject constructor() {

    fun convert(movieDto: MovieDTO): MovieUI =
        MovieUI(
            id = movieDto.id.orDefault(0),
            title = movieDto.title.orEmpty(),
            originalTitle = movieDto.originalTitle.orEmpty(),
            releaseDate = movieDto.releaseDate.orEmpty().getYear(),
            voteAverage = movieDto.voteAverage.orDefault(0f),
            overview = movieDto.overview.orEmpty(),
            runtime = movieDto.runtime?: 0,
            backdropPath = movieDto.backdropPath.orEmpty(),
            posterPath = Constants.BASE_IMAGE_URL.plus(movieDto.posterPath.orEmpty())
        )

    fun convertToMovieDetail(movieWithCast: MovieWithCastEntity): MovieDetailsUI =
        MovieDetailsUI(
            id = movieWithCast.movie.id.orDefault(0),
            adult = movieWithCast.movie.adult.orFalse(),
            title = movieWithCast.movie.title.orEmpty(),
            releaseDate = movieWithCast.movie.releaseDate.orEmpty().getYear(),
            voteAverage = movieWithCast.movie.voteAverage.orDefault(0f),
            overview = movieWithCast.movie.overview.orEmpty(),
            runtime = movieWithCast.movie.runtime.getFormattedRuntime(),
            director = movieWithCast.movie.director.orEmpty(),
            genres = movieWithCast.movie.genres.orEmpty().map { convert(it) },
            backdropPath = Constants.BASE_IMAGE_URL.plus(movieWithCast.movie.backdropPath.orEmpty()),
            posterPath = Constants.BASE_IMAGE_URL.plus(movieWithCast.movie.posterPath.orEmpty()),
            cast = movieWithCast.cast.map{ convert(it) },
            isFavorite = movieWithCast.movie.isFavorite
        )

    private fun convert(castEntity: CastEntity): CastUI =
        CastUI(
            id = castEntity.id.orDefault(0),
            castId = castEntity.castId.orDefault(0),
            name = castEntity.name.orEmpty(),
            profilePath = Constants.BASE_IMAGE_URL.plus(castEntity.profilePath.orEmpty()),
            character = castEntity.character.orEmpty()
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
