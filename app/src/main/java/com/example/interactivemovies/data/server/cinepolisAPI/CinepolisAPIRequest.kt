package com.example.interactivemovies.data.server.cinepolisAPI


data class TransactionRequest(
    val card_number: String,
    val country_code: String = "MX",
    val pin: String ="4804",
    val transaction_include: Boolean = true
)