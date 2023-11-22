package com.project.freshtomatoes.ui.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.util.Date

class AuthRepositoryFirebase(private val auth: FirebaseAuth) : AuthRepository {
    private val currentUserStateFlow = MutableStateFlow(auth.currentUser?.toUser())

    init {
        auth.addAuthStateListener { firebaseAuth ->
            currentUserStateFlow.value = firebaseAuth.currentUser?.toUser()
        }
    }

    override fun currentUser(): StateFlow<User?> {
        return currentUserStateFlow
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            var result = auth.signInWithEmailAndPassword(email, password).await()
            var user = result.user
            println("The user is: ${user?.email}")
            return user != null
        } catch (e: FirebaseAuthInvalidUserException) {
            println("The user doesn't exist: ${e.message}")
            return false
        }
        // If the email is badly formatted it counts as this type of exception
        catch (e: FirebaseAuthInvalidCredentialsException) {
            println("Invalid credentials: ${e.message}")
            return false
        } catch (e: Exception) {
            println("********************The exception is: " + e.message)
            return false
        }
    }

    override fun signOut() {
        return auth.signOut()
    }

    override suspend fun delete() {
        if (auth.currentUser != null) {
            auth.currentUser!!.delete()
        }
    }
}

/** Convert from FirebaseUser to User */
private fun FirebaseUser?.toUser(): User? {
    return this?.let {
        if (it.email == null) {
            null
        } else {
            User(
                email = it.email!!,
                uid = it.uid,
                dateJoined =  Date(it.metadata!!.creationTimestamp)
            )
        }
    }
}
