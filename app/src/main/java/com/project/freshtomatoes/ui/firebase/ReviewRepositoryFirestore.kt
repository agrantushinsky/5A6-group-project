package com.project.freshtomatoes.ui.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.project.freshtomatoes.data.Review

class ReviewRepositoryFirestore(val db: FirebaseFirestore): ReviewRepository {
    val dbReviews: CollectionReference = db.collection("reviews")

    override fun saveReview(review: Review) {
        dbReviews.add(review)
    }

    override fun getReviews(): List<Review> {
        return emptyList()
    }
}