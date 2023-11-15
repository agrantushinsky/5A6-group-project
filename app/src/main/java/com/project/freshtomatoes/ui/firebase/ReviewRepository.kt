package com.project.freshtomatoes.ui.firebase

import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun saveReview(review: Review)
    fun getReviews(): List<Review>
    fun getAverageRating(movieId: Int): Flow<Double>
}
