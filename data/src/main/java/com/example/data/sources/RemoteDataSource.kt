package com.example.data.sources

import com.example.domain.Either
import com.example.domain.Movie
import com.example.domain.User

interface RemoteDataSource {
    suspend fun loginUser(userName: String, password: String): Either<String, User>
    suspend fun userProfile(tokenType: String, token: String): Either<String, User>
    suspend fun userTransactions(tokenType: String, token: String, cardNo: String)
    suspend fun getListings(): Either<String, List<Movie>>
}