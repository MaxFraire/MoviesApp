package com.maxfraire.movies.data.remote.models

enum class MovieListType(val type: String) {
    Popular("popular"),
    InTheaters("now_playing"),
    TopRated("top_rated"),
    Upcoming("upcoming")
}