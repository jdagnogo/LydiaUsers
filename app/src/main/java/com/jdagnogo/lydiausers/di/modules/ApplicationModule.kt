package com.jdagnogo.lydiausers.di.modules

import android.content.Context
import com.jdagnogo.lydiausers.LydiaUsersApplication
import com.jdagnogo.lydiausers.di.utils.AppContext

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @AppContext
    @Provides
    @Singleton
    fun provideContext(application: LydiaUsersApplication): Context = application
}