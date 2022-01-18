package com.example.data.repositories

import com.example.data.sources.LocalDataSource
import com.example.data.sources.RemoteDataSource
import com.example.domain.Either
import com.example.domain.Movie

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getListings(): Either<String, List<Movie>> {
        if (localDataSource.isListingsEmpty()) {
            when (val response = remoteDataSource.getListings()) {
                is Either.Left -> return Either.Left(response.l)
                is Either.Right -> localDataSource.insertMovieList(response.r)
            }
        }
        return Either.Right(localDataSource.getMovieList())
    }

    suspend fun getMovieByID(movieID: Int): Movie = localDataSource.getMovieByID(movieID)
}