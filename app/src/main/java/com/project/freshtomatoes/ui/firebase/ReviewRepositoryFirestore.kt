package com.project.freshtomatoes.ui.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ReviewRepositoryFirestore(val db: FirebaseFirestore): ReviewRepository {
    val dbReviews: CollectionReference = db.collection("reviews")

    override fun saveReview(review: Review) {
        dbReviews.add(review)
    }

    override fun getReviews(): List<Review> {
        return emptyList()
    }

    override fun getAverageRating(movieId: Int): Flow<Int> = callbackFlow {
        val query = dbReviews.whereEqualTo("movieId", movieId)
            .get()
            .addOnSuccessListener { documents ->
                val averageRating = documents.mapNotNull { it.getDouble("rating") }.average()
            }
            .addOnFailureListener {
                // TODO
            }

    }
}