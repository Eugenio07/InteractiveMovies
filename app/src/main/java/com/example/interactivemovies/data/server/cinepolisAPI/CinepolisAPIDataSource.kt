package com.example.interactivemovies.data.server.cinepolisAPI

import com.example.data.sources.RemoteDataSource
import com.example.domain.Either
import com.example.domain.Movie
import com.example.domain.User
import com.example.interactivemovies.data.toDomainMovie
import com.example.interactivemovies.data.toUser
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CinepolisAPIDataSource : RemoteDataSource {

    override suspend fun loginUser(userName: String, password: String): Either<String, User> =
        withContext(Dispatchers.IO) {
            try {
                val response = CinepolisAPI.RETROFIT_SERVICE.loginUser(
                    username = userName,
                    password = password
                ).toUser()

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
            try {
                Either.Right(
                    CinepolisAPI.RETROFIT_SERVICE.getUserProfile(auth = "$tokenType $token")
                        .toUser()
                )
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }

    override suspend fun userTransactions(tokenType: String, token: String, cardNo: String) {
        withContext(Dispatchers.IO) {
            try {
                Either.Right(
                    CinepolisAPI.RETROFIT_SERVICE.getUserTransactions(auth = "$tokenType $token", transactionRequest = TransactionRequest(cardNo))
                )
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }
    }

    override suspend fun getListings(): Either<String, List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = CinepolisAPI.RETROFIT_SERVICE.getListings().toDomainMovie()
                Either.Right(response)
            } catch (e: HttpException) {
                Logger.e(e.message())
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }


}