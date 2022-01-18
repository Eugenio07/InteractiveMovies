package com.example.interactivemovies.data

import com.example.domain.Movie
import com.example.domain.User
import com.example.interactivemovies.data.server.cinepolisAPI.UserLoginResponse
import com.example.interactivemovies.data.database.entity.UserDB
import com.example.interactivemovies.data.server.cinepolisAPI.ListingsResponse
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
            cast = movie.cast.map { it.toDomainCast() },
            cinemas = movie.cinemas,
            position = movie.position,
            categories = movie.categories,
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


//fun ListingsResponse.Movie.Media.toDomainMedia(): Listings.Movie.Media =
//    Listings.Movie.Media(resource, type, code)

fun ListingsResponse.Movie.Cast.toDomainCast(): Movie.Cast =
    Movie.Cast(label, value)

//fun ListingsResponse.Movie.toDomainMovie(): Movie =
//    Movie(
//        rating,
//        cast.map { it.toDomainCast() },
//        cinemas,
//        position,
//        categories,
//        genre,
//        synopsis,
//        length,
//        release_date,
//        distributor,
//        id,
//        name,
//        code,
//        original_name
//    )

//fun ListingsResponse.Route.Sizes.toDomainSizes(): Listings.Route.Sizes =
//    Listings.Route.Sizes(large, medium, small)
//
//fun ListingsResponse.Route.toDomainRoutes(): Listings.Route =
//    Listings.Route(code, sizes.toDomainSizes())
//
//fun ListingsResponse.toDomainListings(): Listings =
//    Listings(movies.map { it.toDomainMovie() }, routes.map { it.toDomainRoutes() })