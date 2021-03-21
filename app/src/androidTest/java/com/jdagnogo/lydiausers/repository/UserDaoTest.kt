package com.jdagnogo.lydiausers.repository

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.data.LydiaDatabase
import com.jdagnogo.lydiausers.repository.data.UserDao
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var sut: UserDao
    private lateinit var db: LydiaDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LydiaDatabase::class.java
        ).build()
        sut = db.getUserDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAllShouldCreateNewUser() = runBlocking {
        val user = User(name = FAKE_NAME, email = FAKE_EMAIL)

        sut.insertAll(listOf(user))

        val data = sut.getAll().load(PagingSource.LoadParams.Refresh(12, 12, false))
        val result = (data as? PagingSource.LoadResult.Page)?.data?.first()
        assertTrue(result?.email == FAKE_EMAIL)
        assertTrue(result?.name == FAKE_NAME)
    }

    @Test
    @Throws(Exception::class)
    fun clearShouldDeleteAllUser() = runBlocking {
        val user = User(name = FAKE_NAME, email = FAKE_EMAIL)
        sut.insertAll(listOf(user))

        sut.clear()

        val data = sut.getAll().load(PagingSource.LoadParams.Refresh(FAKE_INT, FAKE_INT, false))
        val result = (data as? PagingSource.LoadResult.Page)?.data?.first()
        assertTrue(result?.email == FAKE_EMAIL)
        assertTrue(result?.name == FAKE_NAME)
    }


    companion object {
        const val FAKE_INT= 43
        const val FAKE_NAME = "FAKE_NAME"
        const val FAKE_EMAIL = "FAKE_EMAIL"
    }
}