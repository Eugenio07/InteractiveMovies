package com.example.usecases

import com.example.data.repositories.UserRepository
import com.example.domain.Either
import com.example.domain.User
import com.example.domain.UserTransactions

class UserUseCases(private val userRepository: UserRepository) {
    suspend fun loginUser(userName: String, password: String): Either<String, User> =
        userRepository.loginUser(userName, password)

    suspend fun getUserProfile(): Either<String, User> = userRepository.getUserProfile()

    suspend fun getUserTransactions(cardNo: String): Either<String, UserTransactions> = userRepository.getUserTransactions(cardNo)

}