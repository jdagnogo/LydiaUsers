package com.jdagnogo.lydiausers.di

import androidx.core.view.KeyEventDispatcher
import com.jdagnogo.lydiausers.LydiaUsersApplication
import com.jdagnogo.lydiausers.di.modules.ActivityModule
import com.jdagnogo.lydiausers.di.modules.ApplicationModule
import com.jdagnogo.lydiausers.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: LydiaUsersApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: LydiaUsersApplication)
}