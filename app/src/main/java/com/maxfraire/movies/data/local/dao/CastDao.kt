package com.maxfraire.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.maxfraire.movies.data.local.entities.CastEntity

@Dao
interface CastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(cast: List<CastEntity>)

}