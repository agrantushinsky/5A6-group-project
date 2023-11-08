package com.project.freshtomatoes.ui

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class FreshTomatoes : Application() {
    /* Always be able to access the module ("static") */
    companion object {
        lateinit var appModule: AppModule
    }

    /* Called only once at beginning of application's lifetime */
    override fun onCreate() {
        super.onCreate()
        appModule = AppModule(this, Firebase.auth, FirebaseFirestore.getInstance())
    }
}
