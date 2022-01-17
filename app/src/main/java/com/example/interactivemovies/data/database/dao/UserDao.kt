package com.example.interactivemovies.data.database.dao

import androidx.room.*
import com.example.interactivemovies.data.database.entity.UserDB

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(userDB: UserDB)

    @Update
    fun updateUser(userDB: UserDB)

    @Query("SELECT * FROM UserDB")
    fun getUserFromDB(): UserDB
}