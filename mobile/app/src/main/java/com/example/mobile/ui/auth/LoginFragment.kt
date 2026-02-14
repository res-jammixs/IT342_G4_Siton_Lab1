package com.example.mobile.ui.auth

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
import com.example.mobile.data.model.LoginRequest
import com.example.mobile.data.repository.AuthRepository
import com.example.mobile.data.repository.Result
import com.example.mobile.databinding.FragmentLoginBinding
import com.example.mobile.ui.main.MainActivityNew
import com.example.mobile.util.TokenManager
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var authRepository: AuthRepository
    private lateinit var tokenManager: TokenManager
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        authRepository = AuthRepository(ApiClient.apiService)
        tokenManager = TokenManager(requireContext())
        
        setupListeners()
    }
    
    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            
            if (validateInput(email, password)) {
                login(email, password)
            }
        }
        
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    
    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            showError("Email is required")
            return false
        }
        
        if (password.isEmpty()) {
            showError("Password is required")
            return false
        }
        
        return true
    }
    
    private fun login(email: String, password: String) {
        showLoading(true)
        hideError()
        
        lifecycleScope.launch {
            val result = authRepository.login(LoginRequest(email, password))
            
            showLoading(false)
            
            when (result) {
                is Result.Success -> {
                    tokenManager.saveToken(result.data.token)
                    navigateToMain()
                }
                is Result.Error -> {
                    showError(result.message)
                }
            }
        }
    }
    
    private fun navigateToMain() {
        val intent = Intent(requireContext(), MainActivityNew::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !show
    }
    
    private fun showError(message: String) {
        binding.tvError.text = message
        binding.tvError.visibility = View.VISIBLE
    }
    
    private fun hideError() {
        binding.tvError.visibility = View.GONE
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
