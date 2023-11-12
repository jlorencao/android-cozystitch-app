package com.example.cozystitchapp.ui.home.projects.pages

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.example.cozystitchapp.R
import com.example.cozystitchapp.databinding.ActivityProjectDetailsBinding
import com.example.cozystitchapp.model.Project
import com.example.cozystitchapp.ui.home.projects.viewmodel.ProjectsViewModel
import java.io.Serializable

class ProjectDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProjectDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProjectDetailsBinding.inflate(layoutInflater)
        val view = binding.root


        setContentView(view)

        //setting up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.topBarView)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.topBarView.topBarTitle.text = ""

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_24)
        toolbar.setNavigationOnClickListener{
            finish()
        }

        //handle the action button from toolbar to create a new project
        binding.topBarView.addNewProjectIcon.setOnClickListener {
            val intent = Intent(this, NewProjectActivity::class.java)
            startActivity(intent)
            Log.d("ACTION_BUTTON","button pressed")
        }

        //get the Project from intent
        val project = getSerializable(intent,"Project", Project::class.java)

        //setting the values to the views
        binding.projectNameTextView.text = project.title
        binding.projectTypeTextView.text = project.type
        binding.statusPercentageTextView.text = project.status.toString()
        binding.yarnTextView.text = project.yarn
        binding.hookTextView.text = project.hook
        binding.accessoryTextView.text = project.accessory

        if(project.imagePath?.isNotEmpty() == true){
            //manage to get an image from directory
        } else {
            binding.projectImageView.setImageResource(R.drawable.logo_app)
        }
    }

    //handle to show the pattern instructions




    //Managing the receive of Project object because getSerializableExtra is deprecated to apply other option on android sdk 33 or higher
    private fun <T : Serializable?> getSerializable(intent: Intent, key: String, className: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= 33)
            intent.getSerializableExtra(key, className)!!
        else
            (intent.getSerializableExtra(key) as T)
    }
}

