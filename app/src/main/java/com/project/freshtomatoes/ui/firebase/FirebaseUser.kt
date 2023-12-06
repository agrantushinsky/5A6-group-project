package com.project.freshtomatoes.ui.firebase

import kotlinx.coroutines.flow.StateFlow
import java.util.Date

/**
 * Represents a user in Firebase Authentication.
 */
data class User(
    var email: String,
    var uid: String,
    var dateJoined: Date
)

/**
 * Interface for AuthRepository, templates functionality for all user account operations.
 */
interface AuthRepository {
    // Return a StateFlow so that the composable can always update when
    //   the current authorized user status changes for any reason
    fun currentUser(): StateFlow<User?>
    fun hasCurrentUserDirect(): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signIn(email: String, password: String): Boolean
    fun signOut()
    suspend fun delete()
}
