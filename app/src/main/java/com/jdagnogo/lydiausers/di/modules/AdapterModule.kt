package com.jdagnogo.lydiausers.di.modules

import com.jdagnogo.lydiausers.ui.adapter.UserComparator
import com.jdagnogo.lydiausers.ui.adapter.UserListAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {
    @Provides
    @Singleton
    fun provideUserComparator(): UserComparator = UserComparator()


    @Provides
    @Singleton
    fun provideUserAdapter(comparator: UserComparator): UserListAdapter =
        UserListAdapter(comparator)

}