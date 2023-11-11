package com.example.cozystitchapp.ui.home.projects.pages

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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

        val project = getSerializable(intent,"Project", Project::class.java)

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


    //Managing the receive of Project object because getSerializableExtra is deprecated to apply other option on android sdk 33 or higher
    private fun <T : Serializable?> getSerializable(intent: Intent, key: String, className: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= 33)
            intent.getSerializableExtra(key, className)!!
        else
            (intent.getSerializableExtra(key) as T)
    }
}