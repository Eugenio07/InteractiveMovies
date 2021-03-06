package com.example.interactivemovies.data

import com.example.domain.Movie
import com.example.domain.User
import com.example.domain.UserTransactions
import com.example.interactivemovies.data.database.entity.MovieDB
import com.example.interactivemovies.data.server.cinepolisAPI.UserLoginResponse
import com.example.interactivemovies.data.database.entity.UserDB
import com.example.interactivemovies.data.server.cinepolisAPI.ListingsResponse
import com.example.interactivemovies.data.server.cinepolisAPI.TransactionsResponse
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

fun ListingsResponse.toDomainMovie(): List<Movie> {
    return movies.map { movie ->
        Movie(
            rating = movie.rating,
            position = movie.position,
            genre = movie.genre,
            synopsis = movie.synopsis,
            length = movie.length,
            release_date = movie.release_date,
            distributor = movie.distributor,
            id = movie.id,
            name = movie.name,
            code = movie.code,
            original_name = movie.original_name,
            poster = "${routes[0].sizes.medium}${movie.media[0].resource}",
            video = "${routes[2].sizes.medium}${movie.media[2].resource}"
        )
    }
}

fun Movie.toMovieDB(): MovieDB = MovieDB(
    rating,
    position,
    genre,
    synopsis,
    length,
    release_date,
    distributor,
    id,
    name,
    code,
    original_name,
    poster,
    video
)

fun MovieDB.toMovie(): Movie = Movie(
    rating,
    position,
    genre,
    synopsis,
    length,
    release_date,
    distributor,
    id,
    name,
    code,
    original_name,
    poster,
    video
)

fun TransactionsResponse.toDomainTransactions(): UserTransactions =
    UserTransactions(
        name1 = balance_list[0].name,
        balance1 = balance_list[0].balance,
        name2 = balance_list[1].name,
        balance2 = "${balance_list[1].balance} ${balance_list[1].message}",
        level = level.name,
        nextLevel = level.next_level,
        message = level.message
    )