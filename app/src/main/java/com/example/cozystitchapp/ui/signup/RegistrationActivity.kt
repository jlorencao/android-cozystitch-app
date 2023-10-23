package com.example.cozystitchapp.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cozystitchapp.R
import com.example.cozystitchapp.databinding.ActivityRegistrationBinding
import com.example.cozystitchapp.ui.signup.registration.SignUpFragment

class RegistrationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragmentContainerView,
                SignUpFragment.newInstance("parametro 1", "parametro 2"))
            .commit()
    }
}

