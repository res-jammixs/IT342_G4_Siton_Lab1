package com.example.mobile.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.databinding.ActivityMainNewBinding

class MainActivityNew : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainNewBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
