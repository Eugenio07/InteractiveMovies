package com.example.interactivemovies.data

import com.example.domain.User
import com.example.interactivemovies.data.server.cinepolisAPI.UserLoginResponse
import com.example.interactivemovies.data.database.entity.UserDB
import com.example.interactivemovies.data.server.cinepolisAPI.UserProfileResponse

fun User.toUserDB(): UserDB = UserDB(
    userName,
    firstName,
    lastName,
    password,
    token,
    tokenType,
    email,
    phone,
    picture,
    cardNumber,
    countryCode
)

fun UserDB.toUser(): User = User(
    userName,
    firstName,
    lastName,
    password,
    token,
    token_type,
    email,
    phone,
    picture,
    cardNumber,
    country_code
)

fun UserLoginResponse.toUser(): User = User(
    token = access_token,
    tokenType = token_type,
    userName = username,
    countryCode = country_code
)

fun UserProfileResponse.toUser(): User = User(
    email = email,
    firstName = first_name,
    lastName = last_name,
    phone = phone_number,
    picture = profile_picture,
    cardNumber = card_number
)