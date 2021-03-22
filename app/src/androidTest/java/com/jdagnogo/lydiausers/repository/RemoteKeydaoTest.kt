package com.jdagnogo.lydiausers.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jdagnogo.lydiausers.repository.UserDaoTest.Companion.FAKE_INT
import com.jdagnogo.lydiausers.repository.UserDaoTest.Companion.FAKE_NAME
import com.jdagnogo.lydiausers.repository.data.LydiaDatabase
import com.jdagnogo.lydiausers.model.RemoteKeys
import com.jdagnogo.lydiausers.repository.data.RemoteKeysDao
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This class is basically the same as UserDaoTest in term of structure
 * As I dont have lot of time I will not test all method.
 * But in a real project of course I would have
 */
@RunWith(AndroidJUnit4::class)
class RemoteKeydaoTest {
    private lateinit var sut: RemoteKeysDao
    private lateinit var db: LydiaDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LydiaDatabase::class.java
        ).build()
        sut = db.getRemoteKeysDao()
    }

    @Test
    @Throws(Exception::class)
    fun insertOneShouldCreateOnlyOneRemoteKey() = runBlocking {
        val remoteKeys = RemoteKeys(
            FAKE_NAME,
            FAKE_INT,
            FAKE_INT + 1
        )
        sut.insertAll(listOf(remoteKeys))

        val data = sut.remoteKeysRepoId(FAKE_NAME)

        assertTrue(data?.repoId == FAKE_NAME)
        assertTrue(data?.nextKey == FAKE_INT + 1)
        assertTrue(data?.prevKey == FAKE_INT)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}