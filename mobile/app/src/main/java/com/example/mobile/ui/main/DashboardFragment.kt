package com.example.mobile.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobile.R
import com.example.mobile.data.api.ApiClient
import com.example.mobile.data.repository.AuthRepository
import com.example.mobile.databinding.FragmentDashboardBinding
import com.example.mobile.ui.auth.AuthActivity
import com.example.mobile.util.TokenManager
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var tokenManager: TokenManager
    private lateinit var authRepository: AuthRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        tokenManager = TokenManager(requireContext())
        authRepository = AuthRepository(ApiClient.apiService)
        
        setupListeners()
    }
    
    private fun setupListeners() {
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
        }
        
        binding.btnLogout.setOnClickListener {
            logout()
        }
    }
    
    private fun logout() {
        lifecycleScope.launch {
            val token = tokenManager.getToken()
            if (token != null) {
                authRepository.logout(token)
            }
            
            tokenManager.clearToken()
            navigateToAuth()
        }
    }
    
    private fun navigateToAuth() {
        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
