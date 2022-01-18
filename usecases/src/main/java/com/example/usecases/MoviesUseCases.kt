package com.example.usecases

import com.example.data.repositories.MoviesRepository
import com.example.domain.Either
import com.example.domain.Movie

class MoviesUseCases(private val moviesRepository: MoviesRepository) {
    suspend fun getListings(): Either<String, List<Movie>> = moviesRepository.getListings()
    suspend fun getMovieByID(movieID: Int): Movie = moviesRepository.getMovieByID(movieID)
}