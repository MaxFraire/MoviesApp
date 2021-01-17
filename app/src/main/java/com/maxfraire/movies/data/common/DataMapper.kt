package com.maxfraire.movies.data.common

import com.maxfraire.movies.data.local.entities.CastEntity
import com.maxfraire.movies.data.local.entities.MovieEntity
import com.maxfraire.movies.data.remote.models.CastDTO
import com.maxfraire.movies.data.remote.models.MovieDTO
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun convert(movieDto: MovieDTO): MovieEntity =
        MovieEntity(
            id = movieDto.id,
            adult = movieDto.adult,
            backdropPath = movieDto.backdropPath,
            overview = movieDto.overview,
            posterPath = movieDto.posterPath,
            releaseDate = movieDto.releaseDate,
            runtime = movieDto.runtime,
            title = movieDto.title,
            voteAverage = movieDto.voteAverage,
            voteCount = movieDto.voteCount,
            director = movieDto.credits?.crew?.firstOrNull {
                it.job.toLowerCase() == DIRECTOR
            }?.name.orEmpty(),
            isFavorite = false,
            genres = movieDto.genres
        )

    fun convert(castDto: CastDTO, movieId: Int): CastEntity =
        CastEntity(
            id = castDto.id,
            castId = castDto.castId,
            creditId = castDto.creditId,
            name = castDto.name,
            profilePath = castDto.profilePath,
            character = castDto.character,
            movieId = movieId
        )

    companion object {
        private const val DIRECTOR = "director"
    }
}