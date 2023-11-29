package com.project.freshtomatoes.ui.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ReviewRepositoryFirestore(val db: FirebaseFirestore) : ReviewRepository {
    val dbReviews: CollectionReference = db.collection("reviews")

    override fun saveReview(review: Review) {
        dbReviews.add(review)
    }

    override fun editReview(oldReview: Review, newReview: Review) {
        dbReviews
            .whereEqualTo("ownerUID", oldReview.ownerUID)
            .whereEqualTo("movieId", oldReview.movieId)
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach {
                    dbReviews.document(it.id).set(newReview)
                }
            }
    }

    override fun deleteReview(review: Review) {
        dbReviews
            .whereEqualTo("ownerUID", review.ownerUID)
            .whereEqualTo("movieId", review.movieId)
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach {
                    dbReviews.document(it.id).delete()
                }
            }
    }

    override fun getReviewsByUID(uid: String): Flow<List<Review>> = callbackFlow {
        val subscription = dbReviews
            .whereEqualTo("ownerUID", uid)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    println("Listen failed $error")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val reviews = snapshot.toObjects(Review::class.java)
                    if (reviews != null) {
                        trySend(reviews)
                    }
                } else {
                    println("Reviews collection does not exist")
                    trySend(emptyList())
                }
            }
        awaitClose { subscription.remove() }
    }

    override fun getReviewsByMovieID(movieId: Int): Flow<List<Review>> = callbackFlow {
        val subscription = dbReviews
            .whereEqualTo("movieId", movieId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    println("Listen failed $error")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val reviews = snapshot.toObjects(Review::class.java)
                    if (reviews != null) {
                        trySend(reviews)
                    }
                } else {
                    println("Reviews collection does not exist")
                    trySend(emptyList())
                }
            }
        awaitClose { subscription.remove() }
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
