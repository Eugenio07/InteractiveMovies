package com.example.data.repositories

import com.example.data.sources.LocalDataSource
import com.example.data.sources.RemoteDataSource
import com.example.domain.Either
import com.example.domain.User

class UserRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getUserFromDB() = localDataSource.getUser()

    suspend fun loginUser(userName: String, password: String): Either<String, User> =
        when (val response = remoteDataSource.loginUser(userName, password)) {
            is Either.Left -> response
            is Either.Right -> {
                localDataSource.insertUser(response.r)
                response
            }
        }

    suspend fun getUserProfile(): Either<String, User> {
        val user = localDataSource.getUser()

        return if (user.firstName.isNotEmpty()) {
            Either.Right(user)
        } else {
            when (val response = remoteDataSource.userProfile(user.tokenType, user.token)) {
                is Either.Left -> response
                is Either.Right -> {
                    with(response.r) {
                        localDataSource.updateUser(
                            user.copy(
                                email = email,
                                firstName = firstName,
                                lastName = lastName,
                                phone = phone,
                                picture = picture,
                                cardNumber = cardNumber
                            )
                        )
                    }
                    response
                }
            }
        }
    }
}