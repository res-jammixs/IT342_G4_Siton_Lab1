package com.example.mobile.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobile.R
import com.example.mobile.data.api.ApiClient
import com.example.mobile.data.model.RegisterRequest
import com.example.mobile.data.repository.AuthRepository
import com.example.mobile.data.repository.Result
import com.example.mobile.databinding.FragmentRegisterBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var authRepository: AuthRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        authRepository = AuthRepository(ApiClient.apiService)
        
        setupListeners()
    }
    
    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val middleName = binding.etMiddleName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            
            if (validateInput(firstName, lastName, email, password)) {
                register(firstName, middleName, lastName, email, phone, password)
            }
        }
        
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
    
    private fun validateInput(
        firstName: String, 
        lastName: String, 
        email: String, 
        password: String
    ): Boolean {
        if (firstName.isEmpty()) {
            showError("First name is required")
            return false
        }
        
        if (lastName.isEmpty()) {
            showError("Last name is required")
            return false
        }
        
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
    
    private fun register(
        firstName: String,
        middleName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String
    ) {
        showLoading(true)
        hideError()
        hideSuccess()
        
        lifecycleScope.launch {
            val request = RegisterRequest(
                firstName = firstName,
                middleName = middleName.ifEmpty { null },
                lastName = lastName,
                email = email,
                password = password,
                phoneNumber = phone.ifEmpty { null }
            )
            
            val result = authRepository.register(request)
            
            showLoading(false)
            
            when (result) {
                is Result.Success -> {
                    showSuccess("Account created. You can sign in now.")
                    delay(1500)
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                is Result.Error -> {
                    showError(result.message)
                }
            }
        }
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnRegister.isEnabled = !show
    }
    
    private fun showError(message: String) {
        binding.tvError.text = message
        binding.tvError.visibility = View.VISIBLE
    }
    
    private fun hideError() {
        binding.tvError.visibility = View.GONE
    }
    
    private fun showSuccess(message: String) {
        binding.tvSuccess.text = message
        binding.tvSuccess.visibility = View.VISIBLE
    }
    
    private fun hideSuccess() {
        binding.tvSuccess.visibility = View.GONE
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
