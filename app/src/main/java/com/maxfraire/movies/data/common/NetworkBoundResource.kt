package com.maxfraire.movies.data.common

import com.maxfraire.movies.BuildConfig
import com.maxfraire.movies.data.remote.api.HttpCodes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
                    response.message(),
                    response.code()
                )
        }
    } catch (e: Exception) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
        Resource.Error(
            errorCode = HttpCodes.EXCEPTION_RESPONSE_CODE, data = null,
            message = e.message.orEmpty()
        )
    }
