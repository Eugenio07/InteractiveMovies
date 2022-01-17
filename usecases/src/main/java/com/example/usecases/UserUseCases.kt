package com.example.usecases

import com.example.data.repositories.UserRepository
import com.example.domain.Either
import com.example.domain.User

class UserUseCases(private val userRepository: UserRepository) {
    suspend fun loginUser(userName: String, password: String): Either<String, User> =
        userRepository.loginUser(userName, password)

    suspend fun getUserFromDB(): User = userRepository.getUserFromDB()

    suspend fun getUserProfile(): Either<String, User> = userRepository.getUserProfile()

}