package com.project.freshtomatoes.ui.firebase

import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ReviewRepository {
    fun saveReview(review: Review)
    fun getReviews(uid : String): Flow<List<Review>>
    fun getAverageRating(movieId: Int): Flow<Double>
}
