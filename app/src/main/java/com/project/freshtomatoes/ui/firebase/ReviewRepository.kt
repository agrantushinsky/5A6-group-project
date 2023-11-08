package com.project.freshtomatoes.ui.firebase

import com.project.freshtomatoes.data.Review

interface ReviewRepository {
    fun saveReview(review: Review)
    fun getReviews(): List<Review>
    fun getAverageRating(movieId: Int): Int
}