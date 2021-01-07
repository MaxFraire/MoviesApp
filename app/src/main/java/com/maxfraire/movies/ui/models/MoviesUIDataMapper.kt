package com.maxfraire.movies.ui.models

import android.annotation.SuppressLint
import com.maxfraire.movies.data.remote.models.CastDTO
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

    @SuppressLint("DefaultLocale")
    fun convertToMovieDetail(movieDto: MovieDTO): MovieDetailsUI =
        MovieDetailsUI(
            id = movieDto.id.orDefault(0),
            adult = movieDto.adult.orFalse(),
            title = movieDto.title.orEmpty(),
            originalTitle = movieDto.originalTitle.orEmpty(),
            releaseDate = movieDto.releaseDate.orEmpty().getYear(),
            voteAverage = movieDto.voteAverage.orDefault(0f),
            overview = movieDto.overview.orEmpty(),
            runtime = movieDto.runtime.getFormattedRuntime(),
            tagline = movieDto.tagline.orEmpty(),
            director = movieDto.credits?.crew?.firstOrNull {
                it.job.toLowerCase() == DIRECTOR
            }?.name.orEmpty(),
            genres = movieDto.genres.orEmpty().map { convert(it) },
            backdropPath = Constants.BASE_IMAGE_URL.plus(movieDto.backdropPath.orEmpty()),
            posterPath = Constants.BASE_IMAGE_URL.plus(movieDto.posterPath.orEmpty()),
            cast = movieDto.credits?.cast?.map { convert(it) }.orEmpty()

        )

    private fun convert(castDTO: CastDTO): CastUI =
        CastUI(
            id = castDTO.id.orDefault(0),
            castId = castDTO.castId.orDefault(0),
            name = castDTO.name.orEmpty(),
            profilePath = Constants.BASE_IMAGE_URL.plus(castDTO.profilePath.orEmpty()),
            character = castDTO.character.orEmpty()
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

    companion object {
        private const val DIRECTOR = "director"
    }
}
