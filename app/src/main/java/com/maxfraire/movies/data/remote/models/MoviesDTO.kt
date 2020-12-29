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
//    @SerializedName(value = "production_companies")
//    val productionCompanyModels: List<ProductionCompanyDTO>,
//    @SerializedName(value = "production_countries")
//    val productionCountryModels: List<ProductionCountryDTO>,
    @SerializedName(value = "release_date")
    val releaseDate: String? = null,
    @SerializedName(value = "revenue")
    val revenue: Int? = null,
    @SerializedName(value = "runtime")
    val runtime: String? = null,
//    @SerializedName(value = "spoken_languages")
//    val spokenLanguageModels: List<SpokenLanguageDTO>,
    @SerializedName(value = "status")
    val status: String? = null,
    @SerializedName(value = "tagline")
    val tagline: String? = null,
    @SerializedName(value = "title")
    val title: String? = null,
    @SerializedName(value = "video")
    val video: Boolean? = null,
    @SerializedName(value = "vote_average")
    val voteAverage: String? = null,
    @SerializedName(value = "vote_count")
    val voteCount: Int? = null
)

data class GenreDTO(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String
)
