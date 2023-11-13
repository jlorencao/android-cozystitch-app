package com.example.cozystitchapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cozystitchapp.databinding.ActivityStartBinding
import com.example.cozystitchapp.ui.login.LoginActivity
import com.example.cozystitchapp.ui.signup.RegistrationActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.singUpButton.setOnClickListener {
            //navigate to registration
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.singInButton.setOnClickListener {

            //navigate to login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}