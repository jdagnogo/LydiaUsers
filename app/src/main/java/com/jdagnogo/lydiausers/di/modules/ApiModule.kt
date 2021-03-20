package com.jdagnogo.lydiausers.di.modules

import com.google.gson.Gson
import com.jdagnogo.lydiausers.di.utils.API
import com.jdagnogo.lydiausers.repository.api.UserApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideAuthInterceptor() = HttpLoggingInterceptor()

    @API
    @Provides
    @Singleton
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideUserApi(
        @API okhttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) = createRetrofit(okhttpClient, gsonConverterFactory).create(UserApi::class.java)

    private fun createRetrofit(
        okhttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}