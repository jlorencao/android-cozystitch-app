package com.example.cozystitchapp.ui.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cozystitchapp.databinding.ActivityLoginBinding
import com.example.cozystitchapp.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.singInButton.setOnClickListener{
            val email = binding.emailEditText.text.trim().toString()
            val password = binding.passwordEditText.text.trim().toString()
            login(email,password)

        }


        }

    private fun login(email: String, password: String){

        binding.singInButton.isEnabled = false

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

               //progress?.visibility = View.GONE
                binding.singInButton.isEnabled = true

                if (task.isSuccessful) {
                    //Il login è andato a buon fine

                    val user = auth.currentUser

                    if (user != null){
                        val token = user.uid
                    }

                    Log.d("FIREBASE_AUTH", "Login OK")

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                } else {

                    //login failed
                    Log.e("FIREBASE_AUTH", "Login Failed")
                    Snackbar.make(findViewById(android.R.id.content), "Login Failed", Snackbar.LENGTH_LONG).show()
                }
            }
    }

}