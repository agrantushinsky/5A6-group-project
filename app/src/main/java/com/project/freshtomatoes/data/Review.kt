package com.project.freshtomatoes.data

data class Review(
    val movieId: Int,
    val review: String,
    val rating: Int,
    val ownerUID: String
)