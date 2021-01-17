package com.maxfraire.movies.data.local.dao

import androidx.room.*
import com.maxfraire.movies.data.local.entities.MovieEntity
import com.maxfraire.movies.data.local.entities.MovieWithCastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("select * from MovieEntity where id = :movieId")
    suspend fun getMovie(movieId: Int): MovieEntity?

    @Transaction
    @Query("select * from MovieEntity where id = :movieId")
    fun getMovieWithCast(movieId: Int): Flow<MovieWithCastEntity?>

    @Query("UPDATE MovieEntity SET is_favorite = :isFavorite WHERE id = :movieId")
    suspend fun favoriteMovie(movieId: Int, isFavorite: Boolean): Int

}