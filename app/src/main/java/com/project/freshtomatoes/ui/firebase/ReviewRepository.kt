package com.project.freshtomatoes.ui.firebase

import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.flow.Flow
//Coded by Jose
/**
 * ReviewRepository templates all functionality for managing reviews in the Firestore database.
 */
interface ReviewRepository {
    fun saveReview(review: Review)
    fun editReview(oldReview: Review, newReview: Review)
    fun deleteReview(review: Review)
    fun getReviewsByUID(uid: String): Flow<List<Review>>
    fun getReviewsByMovieID(movieId: Int): Flow<List<Review>>
    fun getAverageRating(movieId: Int): Flow<Double>
}
