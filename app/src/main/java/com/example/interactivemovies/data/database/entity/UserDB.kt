package com.example.interactivemovies.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDB(
    @PrimaryKey
    val userName: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val token: String,
    val token_type: String,
    val email: String,
    val phone: String,
    val picture: String,
    val cardNumber: String,
    val country_code: String
)