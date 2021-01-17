package com.maxfraire.movies.data.remote.models

import com.google.gson.annotations.SerializedName

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
