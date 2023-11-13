package com.example.cozystitchapp.ui.home.projects.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cozystitchapp.model.CrochetPattern
import com.example.cozystitchapp.model.Project
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class ProjectsViewModel : ViewModel() {

    var isSaved = MutableLiveData<Boolean>()
    private val _projectList = MutableLiveData<List<Project>>()

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser
    var userId: String? = null
    val patternsDb = Firebase.database.getReference("patterns")

    fun saveNewProject(title: String, type: String, yarn: String, hook: String, accessory: String, crochetPatternName: String) {
        var crochetPattern: CrochetPattern
        val project = Project()

        Log.d("PatternName", crochetPatternName)

        patternsDb.orderByChild("name").equalTo(crochetPatternName.trim())
            .addListenerForSingleValueEvent( object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                   val crochetPatternList = snapshot.children.map { dataSnapshot ->
                       dataSnapshot.getValue(CrochetPattern::class.java)

                   }
                    Log.d("PatternList", crochetPatternList.toString())

                    if(crochetPatternList.isNotEmpty()){
                        crochetPattern = crochetPatternList[0]!!
                        Log.d("PatternList_InsideIF", crochetPattern.toString())

                        project.title = title
                        project.type = type
                        project.yarn = yarn
                        project.hook = hook
                        project.accessory = accessory
                        project.crochetPattern = crochetPattern

                        if (user != null) {
                            userId = user.uid

                            val projectsColectionRef = db.collection("users").document(userId!!).collection("projects")
                            projectsColectionRef.add(project)
                                .addOnSuccessListener {
                                    isSaved.postValue(true)
                                    Log.d("SAVE_PROJECTS_STATUS", "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w("SAVE_PROJECTS_STATUS", "Error writing document", e) }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun getProjectsList(){
        if (user != null) {
            userId = user.uid

            val projectsColectionRef = db.collection("users").document(userId!!).collection("projects")
            val projectList = arrayListOf<Project>()

            projectsColectionRef.
                 addSnapshotListener(object : EventListener<QuerySnapshot> {
                      override fun onEvent(
                          value: QuerySnapshot?,
                          error: FirebaseFirestoreException?
                      ) {
                          if (error != null) {
                              Log.e("Firestore_error",error.message.toString())
                              return
                          }
                          for (dc: DocumentChange in value?.documentChanges!!){
                              if(dc.type == DocumentChange.Type.ADDED){
                                  projectList.add(dc.document.toObject(Project::class.java))
                              }
                          }
                          _projectList.postValue(projectList)
                }
            })
        }
    }

    fun getIsSavedLiveData() : LiveData<Boolean> {
        return isSaved
    }

    fun getProjectListLiveData() : LiveData<List<Project>> {
        return _projectList
    }
}
