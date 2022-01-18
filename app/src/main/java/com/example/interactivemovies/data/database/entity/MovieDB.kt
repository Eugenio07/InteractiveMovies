package com.example.interactivemovies.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDB(
    val rating: String = "",
    val position: Int,
    val genre: String = "",
    val synopsis: String = "",
    val length: String = "",
    val release_date: String = "",
    val distributor: String = "",
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val code: String = "",
    val original_name: String = "",
    val poster: String = "",
    val video: String = "",
)