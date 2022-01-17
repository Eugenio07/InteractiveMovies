package com.example.interactivemovies.data.server.cinepolisAPI


data class UserLoginResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val username: String,
    val country_code: String,
)

data class UserProfileResponse(
    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val profile_picture: String,
    val card_number: String
)