package com.example.mobile.data.api

import com.example.mobile.data.model.LoginRequest
import com.example.mobile.data.model.LoginResponse
import com.example.mobile.data.model.RegisterRequest
import com.example.mobile.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Unit>
    
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    
    @GET("api/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<User>
    
    @POST("api/auth/logout")
    suspend fun logout(@Header("Authorization") token: String): Response<Unit>
}
