package com.maxfraire.movies.ui.models

data class MovieUI(
    val id: Int,
    val backdropPath: String,
    val genres: List<GenreUI> = listOf(),
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: String,
    val title: String,
    val voteAverage: String
)

data class GenreUI(
    val id: Int,
    val name: String
)
