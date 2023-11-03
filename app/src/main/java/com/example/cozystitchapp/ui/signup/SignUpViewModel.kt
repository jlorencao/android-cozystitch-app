package com.example.cozystitchapp.ui.signup

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.cozystitchapp.R
import com.example.cozystitchapp.model.Project
import com.example.cozystitchapp.model.User
import com.example.cozystitchapp.ui.signup.introduction.OnBoardingFirstPageFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase

class SignUpViewModel : ViewModel() {

    //transfer the signup logic here


    //connection with FireStore DB

    fun saveUserData( name: String, lastname: String, levelCrochet: String, country: String, projects: List<Project>?){
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser
        val userId: String?

        if (user != null) {
            userId = user.uid
            val userData = User(name, lastname, levelCrochet, country, projects)
            db.collection("users").document(userId)
                .set(userData, SetOptions.merge())
                .addOnSuccessListener { Log.d("SAVE_DATA_STATUS", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("SAVE_DATA_STATUS", "Error writing document", e) }
        }




    }



}