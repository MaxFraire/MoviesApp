package com.maxfraire.movies.data.remote.models

import com.google.gson.annotations.SerializedName

data class MoviesListDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Long,
    @SerializedName("results")
    val results: List<MovieDTO>
)

data class MovieDTO(
    @SerializedName(value = "id")
    val id: Int? = null,
    @SerializedName(value = "adult")
    val adult: Boolean? = null,
    @SerializedName(value = "backdrop_path")
    val backdropPath: String? = null,
    @SerializedName(value = "budget")
    val budget: Int? = null,
    @SerializedName(value = "genres")
    val genres: List<GenreDTO>? = null,
    @SerializedName(value = "genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName(value = "homepage")
    val homepage: String? = null,
    @SerializedName(value = "imdb_id")
    val imdbId: String? = null,
    @SerializedName(value = "original_language")
    val originalLanguage: String? = null,
    @SerializedName(value = "original_title")
    val originalTitle: String? = null,
    @SerializedName(value = "overview")
    val overview: String? = null,
    @SerializedName(value = "popularity")
    val popularity: Double? = null,
    @SerializedName(value = "poster_path")
    val posterPath: String? = null,
    @SerializedName(value = "release_date")
    val releaseDate: String? = null,
    @SerializedName(value = "revenue")
    val revenue: Int? = null,
    @SerializedName(value = "runtime")
    val runtime: Int? = null,
    @SerializedName(value = "status")
    val status: String? = null,
    @SerializedName(value = "tagline")
    val tagline: String? = null,
    @SerializedName(value = "title")
    val title: String? = null,
    @SerializedName(value = "video")
    val video: Boolean? = null,
    @SerializedName(value = "vote_average")
    val voteAverage: Float? = null,
    @SerializedName(value = "vote_count")
    val voteCount: Int? = null,
    @SerializedName(value = "credits")
    val credits: CreditsDTO? = null
)

data class GenreDTO(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String
)

data class CreditsDTO(
    @SerializedName(value = "id")
    val id: Int? = null,
    @SerializedName(value = "cast")
    val cast: List<CastDTO>? = null,
    @SerializedName(value = "crew")
    val crew: List<CrewDTO>? = null
)

data class CastDTO(
    @SerializedName(value = "id")
    val id: Int? = null,
    @SerializedName(value = "cast_id")
    val castId: Int? = null,
    @SerializedName(value = "credit_id")
    val creditId: String? = null,
    @SerializedName(value = "name")
    val name: String? = null,
    @SerializedName(value = "profile_path")
    val profilePath: String? = null,
    @SerializedName(value = "character")
    val character: String? = null
)

data class CrewDTO(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "credit_id")
    val creditId: String,
    @SerializedName(value = "department")
    val department: String,
    @SerializedName(value = "job")
    val job: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "profile_path")
    val profilePath: String
)
