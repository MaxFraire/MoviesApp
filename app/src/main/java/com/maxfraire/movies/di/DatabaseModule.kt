package com.maxfraire.movies.di

import android.app.Application
import androidx.room.Room
import com.maxfraire.movies.data.local.AppDatabase
import com.maxfraire.movies.data.local.dao.CastDao
import com.maxfraire.movies.data.local.dao.MoviesDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    @ApplicationScope
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "movies_app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideMoviesDao(db: AppDatabase): MoviesDao {
        return db.moviesDao()
    }

    @Provides
    @ApplicationScope
    fun provideCastDao(db: AppDatabase): CastDao {
        return db.castDao()
    }
}