package com.example.mobile.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mobile.data.api.ApiClient
import com.example.mobile.data.model.User
import com.example.mobile.data.repository.AuthRepository
import com.example.mobile.data.repository.Result
import com.example.mobile.databinding.FragmentProfileBinding
import com.example.mobile.util.TokenManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileFragment : Fragment() {
    
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var tokenManager: TokenManager
    private lateinit var authRepository: AuthRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        tokenManager = TokenManager(requireContext())
        authRepository = AuthRepository(ApiClient.apiService)
        
        loadProfile()
    }
    
    private fun loadProfile() {
        showLoading(true)
        hideError()
        
        lifecycleScope.launch {
            val token = tokenManager.getToken()
            
            if (token == null) {
                showError("No authentication token found")
                showLoading(false)
                return@launch
            }
            
            val result = authRepository.getProfile(token)
            
            showLoading(false)
            
            when (result) {
                is Result.Success -> {
                    displayProfile(result.data)
                }
                is Result.Error -> {
                    showError(result.message)
                }
            }
        }
    }
    
    private fun displayProfile(user: User) {
        binding.profileContent.visibility = View.VISIBLE
        
        // Build full name
        val fullName = buildString {
            append(user.firstName)
            if (!user.middleName.isNullOrEmpty()) {
                append(" ${user.middleName}")
            }
            append(" ${user.lastName}")
        }
        
        binding.tvName.text = fullName
        binding.tvEmail.text = user.email
        binding.tvPhone.text = user.phoneNumber ?: "Not set"
        binding.tvStatus.text = if (user.isActive == true) "Active" else "Inactive"
        
        // Format created date
        binding.tvCreated.text = if (user.createdAt != null) {
            try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val date = inputFormat.parse(user.createdAt)
                val outputFormat = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault())
                date?.let { outputFormat.format(it) } ?: "Unknown"
            } catch (e: Exception) {
                user.createdAt
            }
        } else {
            "Unknown"
        }
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }
    
    private fun showError(message: String) {
        binding.tvError.text = message
        binding.tvError.visibility = View.VISIBLE
        binding.profileContent.visibility = View.GONE
    }
    
    private fun hideError() {
        binding.tvError.visibility = View.GONE
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
