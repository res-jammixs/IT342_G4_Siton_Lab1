package com.example.mobile.data.model

data class User(
    val id: Long? = null,
    val firstName: String,
    val middleName: String? = null,
    val lastName: String,
    val email: String,
    val phoneNumber: String? = null,
    val isActive: Boolean? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
