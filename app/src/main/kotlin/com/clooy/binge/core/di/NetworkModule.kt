package com.clooy.binge.core.di

import com.clooy.binge.core.auth.TokenProvider
import com.clooy.binge.core.auth.TokenProviderImpl
import com.clooy.binge.core.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun tokenProvider(): TokenProvider = TokenProviderImpl()

    @Provides
    @Singleton
    fun interceptor(tokenProviderImpl: TokenProvider): Interceptor =
        AuthInterceptor { tokenProviderImpl.getToken() }

    @Provides
    @Singleton
    fun okHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
