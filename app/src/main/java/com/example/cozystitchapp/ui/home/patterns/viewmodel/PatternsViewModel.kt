package com.example.cozystitchapp.ui.home.patterns.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cozystitchapp.model.CrochetPattern
import com.example.cozystitchapp.ui.home.patterns.PatternsPageFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//choose extend Android viewModel to avoid to pass a context to the viewModel
class PatternsViewModel(application: Application) : AndroidViewModel(application) {

   private val _patternList = MutableLiveData<List<CrochetPattern>>()
    private val sharedPrefsKey = "patterns"
    var sharedPreferences: SharedPreferences = application.getSharedPreferences("patterns", Context.MODE_PRIVATE)


    fun getPatterns(){

        val databaseReference : DatabaseReference = Firebase.database.getReference("patterns")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val patternList : List<CrochetPattern> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(CrochetPattern::class.java)!!

                    }
                    _patternList.postValue(patternList)
                    val patternTitleList: List<String> = patternList.map { pattern ->
                        pattern.name.toString()
                    }
                    val patternTitleSet = patternTitleList.toSet()
                    sharedPreferences.edit().putStringSet(sharedPrefsKey, patternTitleSet).apply()



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
