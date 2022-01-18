package com.example.data.sources

import com.example.domain.Either
import com.example.domain.Movie
import com.example.domain.User
import com.example.domain.UserTransactions

interface RemoteDataSource {
    suspend fun loginUser(userName: String, password: String): Either<String, User>
    suspend fun userProfile(tokenType: String, token: String): Either<String, User>
    suspend fun userTransactions(cardNo: String): Either<String, UserTransactions>
    suspend fun getListings(): Either<String, List<Movie>>
}