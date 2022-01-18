package com.example.interactivemovies.data.server.cinepolisAPI

import com.example.interactivemovies.data.server.getRetrofit
import retrofit2.http.*

const val ROOT_URL = "https://stage-api.cinepolis.com"
const val API_KEY = "stage_HNYh3RaK_Test"

const val LOGIN_USER = "/v2/oauth/token"
const val USER_PROFILE = "/v1/members/profile?country_code=MX"
const val GET_LISTINGS = "/v2/movies?country_code=MX&cinemas=61"
const val USER_TRANSACTIONS = "/v1/members/loyalty/"

interface CinepolisAPIService {
    @FormUrlEncoded
    @POST(LOGIN_USER)
    suspend fun loginUser(
        @Header("api_key") apiKey: String = API_KEY,
        @Field("country_code") countryCode: String = "MX",
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String = "password",
        @Field("client_id") clientId: String = "IATestCandidate",
        @Field("client_secret") clientSecret: String = "c840457e777b4fee9b510fbcd4985b68",
    ): UserLoginResponse

    @GET(USER_PROFILE)
    suspend fun getUserProfile(
        @Header("api_key") apiKey: String = API_KEY,
        @Header("Authorization") auth: String
    ): UserProfileResponse

    @POST(USER_TRANSACTIONS)
    suspend fun getUserTransactions(
        @Header("api_key") apiKey: String = API_KEY,
        @Header("Authorization") auth: String, @Body transactionRequest: TransactionRequest
    )

    @GET(GET_LISTINGS)
    suspend fun getListings(@Header("api_key") apiKey: String = API_KEY): ListingsResponse




}

object CinepolisAPI {
    val RETROFIT_SERVICE: CinepolisAPIService by lazy {
        getRetrofit(ROOT_URL).create(CinepolisAPIService::class.java)
    }
}