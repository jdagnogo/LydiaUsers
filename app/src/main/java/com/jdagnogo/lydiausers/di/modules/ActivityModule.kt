package com.jdagnogo.lydiausers.di.modules

import com.jdagnogo.lydiausers.ui.HomeFragment
import com.jdagnogo.lydiausers.ui.MainActivity
import com.jdagnogo.lydiausers.ui.SplashScreenFragment
import com.jdagnogo.lydiausers.ui.UserDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector()
    abstract fun contributeUserDetailsFragment(): UserDetailsFragment

    @ContributesAndroidInjector()
    abstract fun contributeSplashScreenFragment(): SplashScreenFragment
}