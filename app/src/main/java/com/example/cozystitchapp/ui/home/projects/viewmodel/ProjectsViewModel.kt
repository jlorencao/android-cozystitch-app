package com.example.cozystitchapp.ui.home.projects.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cozystitchapp.model.Project
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class ProjectsViewModel : ViewModel() {

    var isSaved = MutableLiveData<Boolean>()
    private val _projectList = MutableLiveData<List<Project>>()
    private val _singleProject = MutableLiveData<Project>()

    val db = Firebase.firestore
    val user = Firebase.auth.currentUser
    var userId: String? = null

    fun saveNewProject(project : Project) {

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
                                  projectList.add(dc.document.toObject((Project::class.java)))
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
