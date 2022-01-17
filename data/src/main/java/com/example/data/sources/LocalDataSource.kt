package com.example.data.source

import com.example.domain.User

interface LocalDataSource {
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun getUser(): User
}