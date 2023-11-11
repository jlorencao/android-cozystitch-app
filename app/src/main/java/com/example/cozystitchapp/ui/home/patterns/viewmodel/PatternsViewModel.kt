package com.example.cozystitchapp.ui.home.patterns.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cozystitchapp.model.CrochetPattern
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PatternsViewModel : ViewModel() {

   private val _patternList = MutableLiveData<List<CrochetPattern>>()

    fun getPatterns(){

        val databaseReference : DatabaseReference = Firebase.database.getReference("patterns")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val patternList : List<CrochetPattern> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(CrochetPattern::class.java)!!
                    }
                    _patternList.postValue(patternList)
                    Log.d("crochetPatternList", _patternList.value.toString())
                } catch (e: Exception){
                    Log.d("Firebase_Exception", e.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getPatternListLiveData(): LiveData<List<CrochetPattern>> {
        return _patternList
    }
}
