package com.maxfraire.movies.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maxfraire.movies.data.remote.models.GenreDTO

class GenreConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromGenresList(genres: List<GenreDTO>?): String? {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenresList(genres: String?): List<GenreDTO>? {
        val listType = object : TypeToken<List<GenreDTO>?>() {}.type
        return Gson().fromJson(genres, listType)
    }
}