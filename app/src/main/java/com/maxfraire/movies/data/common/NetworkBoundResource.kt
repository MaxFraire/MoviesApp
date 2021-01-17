package com.maxfraire.movies.data.common

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.maxfraire.movies.BuildConfig
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType> {

    fun asFlow() = flow {
        emit(Resource.Loading(null))

        val dbValue = loadFromDb()
        if (shouldFetch(dbValue.first())) {
            when (val result = getResource { fetchFromNetwork() } ) {
               is Resource.Success -> {
                   saveNetworkResult(result.data)
                   emitAll(loadFromDb().map { Resource.Success(it) })
               }
                is Resource.Error -> {
                    onFetchFailed()
                    emitAll(loadFromDb().map { Resource.Error(it, result.message) })
                }
            }
        } else {
            emitAll(dbValue.map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {
        // Implement in sub-classes to handle errors
    }

    @WorkerThread
    protected open fun processResponse(response: Resource<RequestType>) = response.data

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(item: RequestType?)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @WorkerThread
    protected abstract suspend fun loadFromDb(): Flow<ResultType?>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Response<RequestType>
}

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
