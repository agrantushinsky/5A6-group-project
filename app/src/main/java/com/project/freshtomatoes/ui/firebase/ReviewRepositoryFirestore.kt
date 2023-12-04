package com.project.freshtomatoes.ui.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.project.freshtomatoes.data.Review
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * ReviewRepositoryFirestore class that implements ReviewRepository interface.
 * Contains all functionality for managing and retrieving reviews on database.
 *
 * @param db FireBaseFireStore instance.
 */
class ReviewRepositoryFirestore(val auth: AuthRepository, val db: FirebaseFirestore) : ReviewRepository {
    val dbReviews: CollectionReference = db.collection("reviews")

    /**
     * Saves a review in the reviews collection.
     *
     * @param review Review to save.
     */
    override fun saveReview(review: Review) {
        if(!auth.hasCurrentUserDirect()) {
            return
        }

        dbReviews.add(review)
    }

    /**
     * Edits an existing review in the reviews collection.
     *
     * @param oldReview Old review to be replaced
     * @param newReview The new Review.
     */
    override fun editReview(oldReview: Review, newReview: Review) {
        if(!auth.hasCurrentUserDirect()) {
            return
        }

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

    /**
     * Deletes the matching review in the reviews collection.
     *
     * @param review The target Review to be deleted.
     */
    override fun deleteReview(review: Review) {
        if(!auth.hasCurrentUserDirect()) {
            return
        }

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

    /**
     * Retrieves all reviews for a given user UID.
     *
     * @param uid The uid to filter reviews by.
     * @return Flow of List<Review> retrieved.
     */
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

    /**
     * Retrieves all reviews for a given movie ID.
     *
     * @param movieId The movie ID to filter reviews by.
     * @return Flow of List<Review> retrieved.
     */
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

    /**
     * Retrieves average rating for a given movieId.
     *
     * @param movieId The movie ID to filter reviews by.
     * @return Flow of Double representing average movie rating.
     */
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
