package com.project.freshtomatoes.data

data class Review(
    val movieId: Int = 0,
    val review: String = "",
    val rating: Int = 0,
    val ownerUID: String = "",
    val reviewDate: String = ""
)
