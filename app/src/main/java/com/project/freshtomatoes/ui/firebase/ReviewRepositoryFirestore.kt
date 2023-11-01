package com.project.freshtomatoes.ui.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class ReviewRepositoryFirestore(val db: FirebaseFirestore): ReviewRepository {
    val dbReviews: CollectionReference = db.collection("reviews")
}