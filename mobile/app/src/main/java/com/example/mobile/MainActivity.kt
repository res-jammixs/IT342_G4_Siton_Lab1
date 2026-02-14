package com.example.mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.ui.auth.AuthActivity
import com.example.mobile.ui.main.MainActivityNew
import com.example.mobile.util.TokenManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Check if user is logged in
        val tokenManager = TokenManager(this)
        
        if (tokenManager.hasToken()) {
            // User is logged in, navigate to main
            val intent = Intent(this, MainActivityNew::class.java)
            startActivity(intent)
        } else {
            // User is not logged in, navigate to auth
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        
        finish()
    }
}