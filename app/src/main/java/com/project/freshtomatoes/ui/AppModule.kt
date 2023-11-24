package com.project.freshtomatoes.ui

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.freshtomatoes.ui.firebase.AuthRepository
import com.project.freshtomatoes.ui.firebase.AuthRepositoryFirebase
import com.project.freshtomatoes.ui.firebase.ReviewRepository
import com.project.freshtomatoes.ui.firebase.ReviewRepositoryFirestore

class AppModule(
    private val appContext: Context,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    /* Create appropriate repository (backed by a DataStore) on first use.
       Only one copy will be created during lifetime of the application. */

    val authRepository: AuthRepository by lazy {
        AuthRepositoryFirebase(auth) // inject Firebase auth
    }

    val reviewRepository: ReviewRepository by lazy {
        ReviewRepositoryFirestore(firestore)
    }
}
