package com.example.interactivemovies.data.server.cinepolisAPI

import com.example.data.sources.RemoteDataSource
import com.example.domain.Either
import com.example.domain.User
import com.example.interactivemovies.data.server.cinepolisAPI.CinepolisAPI
import com.example.interactivemovies.data.server.cinepolisAPI.UserLoginRequest
import com.example.interactivemovies.data.toUser
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CinepolisAPIDataSource : RemoteDataSource {

    override suspend fun loginUser(userName: String, password: String): Either<String, User> =
        withContext(Dispatchers.IO) {
            Logger.d("DataSource userName $userName, password $password")
            try {
                val response = CinepolisAPI.RETROFIT_SERVICE.loginUser(
                    username = userName,
                    password = password
                ).toUser()
                Logger.d("response $response")

                Either.Right(
                    response
                )
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }


    override suspend fun userProfile(tokenType: String, token: String): Either<String, User> =
        withContext(Dispatchers.IO) {
            Logger.d("token = $token")

            try {
                val response = CinepolisAPI.RETROFIT_SERVICE.getUserProfile(
                    auth = "$tokenType $token"
                ).toUser()
                Logger.d("response $response")

                Either.Right(
                    response
                )
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }

}