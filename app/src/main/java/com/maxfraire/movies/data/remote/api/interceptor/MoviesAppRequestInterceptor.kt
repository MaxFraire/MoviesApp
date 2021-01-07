package com.maxfraire.movies.data.remote.api.interceptor

import com.maxfraire.movies.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MoviesAppRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(QUERY_API_KEY, BuildConfig.TMDB_API_KEY)
            .build()

        val request: Request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val QUERY_API_KEY = "api_key"
    }
}