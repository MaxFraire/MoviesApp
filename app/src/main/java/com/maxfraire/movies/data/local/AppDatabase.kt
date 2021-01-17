package com.maxfraire.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maxfraire.movies.data.local.converter.GenreConverter
import com.maxfraire.movies.data.local.dao.CastDao
import com.maxfraire.movies.data.local.dao.MoviesDao
import com.maxfraire.movies.data.local.entities.CastEntity
import com.maxfraire.movies.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class, CastEntity::class], version = 4)
@TypeConverters(value = [GenreConverter::class])
abstract class AppDatabase: RoomDatabase(){

    abstract fun moviesDao(): MoviesDao

    abstract fun castDao(): CastDao
}