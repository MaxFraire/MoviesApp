package com.maxfraire.movies.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieUI(
    val id: Int = 0,
    val backdropPath: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val runtime: Int = 0,
    val title: String = "",
    val voteAverage: Float = 0f
) : Parcelable

data class MovieDetailsUI(
    val id: Int = 0,
    val adult: Boolean = false,
    val backdropPath: String = "",
    val genres: List<GenreUI> = emptyList(),
    val genreIds: List<Int> = emptyList(),
    val homepage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: String = "",
    val status: String = "",
    val title: String = "",
    val voteAverage: Float = 0f,
    val voteCount: Int = 0,
    val director: String = "",
    val cast: List<CastUI> = emptyList(),
    var isFavorite: Boolean = false
)

data class CastUI(
    val id: Int = 0,
    val castId: Int = 0,
    val creditId: String = "",
    val name: String = "",
    val profilePath: String = "",
    val character: String = ""
)

data class GenreUI(
    val id: Int = 0,
    val name: String = ""
)
