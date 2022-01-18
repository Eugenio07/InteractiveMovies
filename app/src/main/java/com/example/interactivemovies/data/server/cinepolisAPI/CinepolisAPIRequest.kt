package com.example.interactivemovies.data.server.cinepolisAPI


data class TransactionRequest(
    val card_number: String,
    val country_code: String = "MX",
    val transaction_include: Boolean = true
)