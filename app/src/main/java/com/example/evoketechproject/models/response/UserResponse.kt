package com.example.evoketechproject.models.response

/**
 * User API response object
 * @author Arnab
 */
data class UserResponse(
    val id: Int = 0,
    val name: String?,
    val email: String?,
    val phone: String?
)