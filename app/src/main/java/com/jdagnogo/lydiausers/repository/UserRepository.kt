package com.jdagnogo.lydiausers.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.api.UserApi
import com.jdagnogo.lydiausers.repository.data.LydiaDatabase
import com.jdagnogo.lydiausers.repository.data.LydiaRemoteMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,
    private val database: LydiaDatabase
) {
    suspend fun startLogin() {
        // I want to load data during the splashScreen.
        // As a first drop I will only wait 2 seconds
        delay(TIME_TO_WAIT)
    }

    fun getUsers(): Flow<PagingData<User>> {
        Log.d("UserRepository", "New query")
        val pagingSourceFactory = { database.getUserDao().getAll() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = LydiaRemoteMediator(api, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        private const val TIME_TO_WAIT = 2000L
        private const val NETWORK_PAGE_SIZE = 50
    }
}