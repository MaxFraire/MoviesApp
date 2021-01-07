package com.maxfraire.movies.di

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.maxfraire.movies.BuildConfig
import com.maxfraire.movies.data.remote.api.interceptor.MoviesAppRequestInterceptor
import com.maxfraire.movies.data.remote.api.MoviesAPI
import com.maxfraire.movies.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(moviesAppRequestInterceptor: MoviesAppRequestInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(moviesAppRequestInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(OkHttpProfilerInterceptor())
            }
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @ApplicationScope
    fun provideMovieService(retrofit: Retrofit) =
        retrofit.create(MoviesAPI::class.java);

    @Provides
    @ApplicationScope
    fun providesMoviesAppInterceptor(): MoviesAppRequestInterceptor =
        MoviesAppRequestInterceptor()

}
