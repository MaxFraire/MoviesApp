package com.maxfraire.movies.data.common

import com.maxfraire.movies.BuildConfig
import retrofit2.Response


suspend fun <R> getResource(call: suspend () -> Response<R>): Resource<R?> =
    try {
        val response = call()
        when {
            response.isSuccessful -> Resource.Success(
                response.body()
            )
            else ->
                Resource.Error(
                    response.body(),
                    response.message()
                )
        }
    } catch (e: Exception) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
        Resource.Error(
            data = null,
            message = e.message.orEmpty()
        )
    }
