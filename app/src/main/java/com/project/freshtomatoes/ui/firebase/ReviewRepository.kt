package com.project.freshtomatoes.ui.firebase

import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun saveReview(review: Review)
    fun getReviewsByUID(uid : String): Flow<List<Review>>
    fun getReviewsByMovieID(movieId: Int): Flow<List<Review>>
    fun getAverageRating(movieId: Int): Flow<Double>
}
