package com.example.interactivemovies.data.database.db

import com.example.data.sources.LocalDataSource
import com.example.domain.Movie
import com.example.domain.User
import com.example.interactivemovies.data.toMovie
import com.example.interactivemovies.data.toMovieDB
import com.example.interactivemovies.data.toUser
import com.example.interactivemovies.data.toUserDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(dataBase: AppDataBase): LocalDataSource {
    private val userDao = dataBase.UserDao()
    private val movieDao = dataBase.MovieDao()

    override suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {userDao.insertUser(user.toUserDB())}
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {userDao.updateUser(user.toUserDB())}
    }

    override suspend fun getUser(): User = withContext(Dispatchers.IO) {userDao.getUserFromDB().toUser()}


    override suspend fun isListingsEmpty(): Boolean =
        withContext(Dispatchers.IO) {movieDao.moviesCount()==0}

    override suspend fun getMovieList(): List<Movie> =
        withContext(Dispatchers.IO) {movieDao.getMovieList().map { it.toMovie() }}

    override suspend fun insertMovieList(movies: List<Movie>) {
        withContext(Dispatchers.IO) {movieDao.insertMovies(movies.map { it.toMovieDB() })}
    }

//    override suspend fun getRouteList(): List<Listings.Route> {
//        TODO("Not yet implemented")
//    }
}