package com.example.data.sources


import com.example.domain.Movie
import com.example.domain.User

interface LocalDataSource {
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun getUser(): User

    suspend fun isListingsEmpty(): Boolean
    suspend fun getMovieList(): List<Movie>
    suspend fun insertMovieList(movies: List<Movie>)
    suspend fun getMovieByID(movieID: Int): Movie
   // suspend fun getRouteList():List<Route>
}