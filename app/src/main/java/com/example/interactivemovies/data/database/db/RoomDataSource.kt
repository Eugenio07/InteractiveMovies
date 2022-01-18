package com.example.interactivemovies.data.database.db

import com.example.data.sources.LocalDataSource
import com.example.domain.Movie
import com.example.domain.User
import com.example.interactivemovies.data.toUser
import com.example.interactivemovies.data.toUserDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(dataBase: AppDataBase): LocalDataSource {
    private val userDao = dataBase.UserDao()

    override suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {userDao.insertUser(user.toUserDB())}
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {userDao.updateUser(user.toUserDB())}
    }

    override suspend fun getUser(): User = withContext(Dispatchers.IO) {userDao.getUserFromDB().toUser()}
    override suspend fun isListingsEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieList(): List<Movie> {
        TODO("Not yet implemented")
    }

//    override suspend fun getRouteList(): List<Listings.Route> {
//        TODO("Not yet implemented")
//    }
}