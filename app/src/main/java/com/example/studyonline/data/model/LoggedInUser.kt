package com.example.studyonline.data.model

import com.example.studyonline.data.bean.Identity

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val id: Int,
    val userId: String,
    val displayName: String,
    val userIdentity: Identity
)