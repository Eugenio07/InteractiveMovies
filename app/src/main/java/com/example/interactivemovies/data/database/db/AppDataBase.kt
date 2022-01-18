package com.example.interactivemovies.data.database.db

import android.content.Context
import androidx.room.*
import com.example.interactivemovies.data.database.dao.MovieDao
import com.example.interactivemovies.data.database.dao.UserDao
import com.example.interactivemovies.data.database.entity.MovieDB
import com.example.interactivemovies.data.database.entity.UserDB


@Database(
    entities = [UserDB::class, MovieDB::class],
    version = 2
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun MovieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "database-app"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}