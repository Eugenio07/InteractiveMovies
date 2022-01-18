package com.example.interactivemovies.data.server.cinepolisAPI


data class UserLoginResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val username: String,
    val country_code: String,
)

data class UserProfileResponse(
    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val profile_picture: String,
    val card_number: String
)

data class ListingsResponse(
    val movies: List<Movie>,
    val routes: List<Route>
) {
    data class Movie(
        val rating: String,
        val media: List<Media>,
        val cast: List<Cast>,
        val cinemas: List<Any>,
        val position: Int,
        val categories: List<String>,
        val genre: String,
        val synopsis: String,
        val length: String,
        val release_date: String,
        val distributor: String,
        val id: Int,
        val name: String,
        val code: String,
        val original_name: String
    ) {
        data class Media(
            val resource: String,
            val type: String,
            val code: String
        )

        data class Cast(
            val label: String,
            val value: List<String>
        )
    }

    data class Route(
        val code: String,
        val sizes: Sizes
    ) {
        data class Sizes(
            val large: String = "",
            val medium: String = "",
            val small: String = "",
          //  val x-large: String
        )
    }
}