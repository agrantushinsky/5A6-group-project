package com.project.freshtomatoes.data

//Coded by Nitpreet
/**
 * Represents a Review in the FireBaseFireStore database.
 * Contains fields:
 * - movieId (Int), review (String), rating (Int), ownerUID (String), and reviewDate (String).
 */
data class Review(
    val movieId: Int = 0,
    val review: String = "",
    val rating: Int = 0,
    val ownerUID: String = "",
    val reviewDate: String = ""
)
