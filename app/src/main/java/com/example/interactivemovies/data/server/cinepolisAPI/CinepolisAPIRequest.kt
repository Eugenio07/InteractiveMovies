package com.example.interactivemovies.data.server.cinepolisAPI

data class UserLoginRequest(
    val country_code: String ="MX",
    val username: String,
    val password: String,
    val grant_type: String = "password",
    val client_id: String = "IATestCandidate",
    val client_secret: String = "c840457e777b4fee9b510fbcd4985b68"
)