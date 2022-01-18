package com.example.interactivemovies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.interactivemovies.data.database.entity.MovieDB

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<MovieDB>)

    @Query("SELECT COUNT(id) FROM MovieDB")
    fun moviesCount(): Int

    @Query("SELECT * FROM MovieDB")
    fun getMovieList(): List<MovieDB>

    @Query("SELECT * FROM MovieDB WHERE id = :movieID")
    fun getMovieByID(movieID:Int): MovieDB
}