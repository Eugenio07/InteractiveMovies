package com.example.domain

data class Movie(
    val rating: String = "",
    val cast: List<Cast>,
    val cinemas: List<Any>,
    val position: Int,
    val categories: List<String>,
    val genre: String = "",
    val synopsis: String = "",
    val length: String = "",
    val release_date: String = "",
    val distributor: String = "",
    val id: Int,
    val name: String = "",
    val code: String = "",
    val original_name: String = "",
    val poster: String = "",
    val video: String = "",
    val url: String = "http://static.cinepolis.com/resources/mx/movies/posters/142x207/38354-242628-20211231120704.jpg",
) {
    data class Cast(
        val label: String = "",
        val value: List<String>
    )
}






