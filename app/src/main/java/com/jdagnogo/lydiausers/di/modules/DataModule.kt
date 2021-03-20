package com.jdagnogo.lydiausers.di.modules

import android.content.Context
import com.jdagnogo.lydiausers.di.utils.API
import com.jdagnogo.lydiausers.di.utils.AppContext
import com.jdagnogo.lydiausers.repository.UserRepository
import com.jdagnogo.lydiausers.repository.api.UserApi
import com.jdagnogo.lydiausers.repository.data.LydiaDatabase
import com.jdagnogo.lydiausers.repository.data.UserDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDb(@AppContext context: Context) = LydiaDatabase.getInstance(context)


    @Provides
    @Singleton
    fun provideUserRepository(
        userApi: UserApi,
        database: LydiaDatabase
    ): UserRepository =
        UserRepository(userApi, database)

    @Singleton
    @Provides
    fun provideUserDao(db: LydiaDatabase) = db.getUserDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(db: LydiaDatabase) = db.getRemoteKeysDao()
}