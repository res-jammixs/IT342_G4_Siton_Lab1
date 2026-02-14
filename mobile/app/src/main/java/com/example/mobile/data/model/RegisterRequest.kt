package com.example.mobile.data.model

data class RegisterRequest(
    val firstName: String,
    val middleName: String? = null,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String? = null
)
