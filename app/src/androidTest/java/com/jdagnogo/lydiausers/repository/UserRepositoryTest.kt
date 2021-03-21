package com.jdagnogo.lydiausers.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.api.UserApi
import com.jdagnogo.lydiausers.repository.data.LydiaDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var sut: UserRepository

    @Mock
    private lateinit var userApi: UserApi
    private lateinit var db: LydiaDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LydiaDatabase::class.java
        ).build()
        sut = UserRepository(userApi, db)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testGetUsers() = runBlocking {
        val user = User(name = UserDaoTest.FAKE_NAME, email = UserDaoTest.FAKE_EMAIL)
        db.getUserDao().insertAll(listOf(user))

        val paging = sut.getUsers().first()

        // assert ????
    }
}