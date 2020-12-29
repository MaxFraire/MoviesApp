package com.maxfraire.movies.data.common

sealed class Resource<out T> {
    abstract val data: T

    data class Success<out R>(override val data: R) : Resource<R>()

    data class Loading<out R>(override val data: R) : Resource<R>()
    data class Error<out E>(
        override val data: E,
        val message: String = "",
        val errorCode: Int
    ) :
        Resource<E>()
}

inline fun <reified T> Resource<T>.doIfError(
    callback: (data: T?, error: String?, errorCode: Int?) ->
    Unit
): Resource<T> = this.apply {
    if (this is Resource.Error) {
        callback(this.data, this.message, this.errorCode)
    }
}

inline fun <reified T> Resource<T>.doIfSuccess(callback: (data: T) -> Unit): Resource<T> =
    this.apply {
        if (this is Resource.Success) {
            callback(this.data)
        }
    }

inline fun <reified T> Resource<T>.doIfLoading(callback: (data: T?) -> Unit): Resource<T> =
    this.apply {
        if (this is Resource.Loading) {
            callback(this.data)
        }
    }