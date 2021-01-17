package com.maxfraire.movies.data.local.entities

import androidx.room.*

@Entity
data class CastEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "cast_id")
    val castId: Int? = null,
    @ColumnInfo(name = "credit_id")
    val creditId: String? = null,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "profile_path")
    val profilePath: String? = null,
    @ColumnInfo(name = "character")
    val character: String? = null,
    @ColumnInfo(name = "movie_id")
    val movieId: Int? = null
)

data class MovieWithCastEntity(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id"
    )
    val cast: List<CastEntity>
)
