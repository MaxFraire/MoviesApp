package com.maxfraire.movies.data.remote.models

import com.google.gson.annotations.SerializedName

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
