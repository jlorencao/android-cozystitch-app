package com.example.cozystitchapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cozystitchapp.R
import com.example.cozystitchapp.ui.home.HomeActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //verify if user is logged in

        val user = Firebase.auth.currentUser

        if (user != null) {
            // User is signed in
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        } else {
            // No user is signed in
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

    }
}