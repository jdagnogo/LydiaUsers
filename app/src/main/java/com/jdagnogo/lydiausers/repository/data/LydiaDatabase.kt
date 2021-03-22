package com.jdagnogo.lydiausers.repository.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jdagnogo.lydiausers.model.RemoteKeys
import com.jdagnogo.lydiausers.model.User

@Database(
    entities = [User::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class LydiaDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

    companion object {
        private const val DB_NAME = "lydia.db"

        @Volatile
        private var INSTANCE: LydiaDatabase? = null

        fun getInstance(context: Context): LydiaDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LydiaDatabase::class.java, DB_NAME
            ).build()
    }

}