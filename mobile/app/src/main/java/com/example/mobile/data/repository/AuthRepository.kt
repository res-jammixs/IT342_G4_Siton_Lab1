package com.example.mobile.data.repository

import com.example.mobile.data.api.ApiService
import com.example.mobile.data.model.LoginRequest
import com.example.mobile.data.model.LoginResponse
import com.example.mobile.data.model.RegisterRequest
import com.example.mobile.data.model.User

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

class AuthRepository(private val apiService: ApiService) {
    
    suspend fun register(request: RegisterRequest): Result<Unit> {
        return try {
            val response = apiService.register(request)
            if (response.isSuccessful) {
                Result.Success(Unit)
            } else {
                Result.Error(response.errorBody()?.string() ?: "Registration failed")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Network error")
        }
    }
    
    suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = apiService.login(request)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.errorBody()?.string() ?: "Login failed")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Network error")
        }
    }
    
    suspend fun getProfile(token: String): Result<User> {
        return try {
            val response = apiService.getProfile("Bearer $token")
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.errorBody()?.string() ?: "Failed to load profile")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Network error")
        }
    }
    
    suspend fun logout(token: String): Result<Unit> {
        return try {
            val response = apiService.logout("Bearer $token")
            if (response.isSuccessful) {
                Result.Success(Unit)
            } else {
                Result.Error(response.errorBody()?.string() ?: "Logout failed")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Network error")
        }
    }
}
