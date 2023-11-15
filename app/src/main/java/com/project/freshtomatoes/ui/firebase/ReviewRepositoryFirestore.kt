package com.project.freshtomatoes.ui.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ReviewRepositoryFirestore(val db: FirebaseFirestore) : ReviewRepository {
    val dbReviews: CollectionReference = db.collection("reviews")

    override fun saveReview(review: Review) {
        dbReviews.add(review)
    }

    override fun getReviews(): List<Review> {
        return emptyList()
    }

    override fun getAverageRating(movieId: Int): Flow<Double> = callbackFlow {
        println(movieId)
        dbReviews.whereEqualTo("movieId", movieId)
            .get()
            .addOnSuccessListener { documents ->
                val averageRating = documents.mapNotNull { it.getDouble("rating") }.average()
                println(averageRating)
                trySend(averageRating)
            }
            .addOnFailureListener {
                // TODO
            }
        awaitClose { }
    }
}
