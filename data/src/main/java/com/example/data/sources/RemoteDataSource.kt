package com.example.data.sources

import com.example.domain.Either
import com.example.domain.User

interface RemoteDataSource {
    suspend fun loginUser(userName: String, password: String): Either<String, User>
    suspend fun userProfile(tokenType: String, token: String): Either<String, User>
}