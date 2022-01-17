package com.example.interactivemovies.data.database.db

import android.content.Context
import androidx.room.*
import com.example.interactivemovies.data.database.dao.UserDao
import com.example.interactivemovies.data.database.entity.UserDB


@Database(
    entities = [UserDB::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun UserDao(): UserDao

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