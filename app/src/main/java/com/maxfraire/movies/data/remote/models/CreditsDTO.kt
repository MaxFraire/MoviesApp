package com.maxfraire.movies.data.remote.models

import com.google.gson.annotations.SerializedName

data class CreditsDTO(
    @SerializedName(value = "id")
    val id: Int? = null,
    @SerializedName(value = "cast")
    val cast: List<CastDTO>? = null,
    @SerializedName(value = "crew")
    val crew: List<CrewDTO>? = null
)
