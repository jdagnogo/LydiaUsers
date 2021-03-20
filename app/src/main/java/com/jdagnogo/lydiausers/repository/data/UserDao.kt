package com.jdagnogo.lydiausers.repository.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jdagnogo.lydiausers.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<User>)

    @Query("SELECT * FROM users")
    fun getAll(): PagingSource<Int, User>

    @Query("DELETE FROM users")
    suspend fun clear()
}