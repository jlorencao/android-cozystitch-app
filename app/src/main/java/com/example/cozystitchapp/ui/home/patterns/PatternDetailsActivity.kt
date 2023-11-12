package com.example.cozystitchapp.ui.home.patterns

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cozystitchapp.R
import com.example.cozystitchapp.databinding.ActivityPatternDetailsBinding
import com.example.cozystitchapp.model.CrochetPattern
import com.example.cozystitchapp.model.Project
import com.example.cozystitchapp.ui.home.patterns.adapter.PatternAdapter
import com.example.cozystitchapp.ui.home.patterns.adapter.PatternInstructionsAdapter
import com.example.cozystitchapp.ui.home.projects.pages.NewProjectActivity
import java.io.Serializable

class PatternDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatternDetailsBinding
    private lateinit var patternRecyclerView: RecyclerView
    lateinit var adapter: PatternInstructionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatternDetailsBinding.inflate(layoutInflater)
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
        //get the Pattern from intent
        val singlePattern = getSerializable(intent,"Crochet Pattern", CrochetPattern::class.java)
        val instructions = singlePattern.rows

        //initializing RecyclerView and Adapter
        patternRecyclerView = binding.instructionRowRecyclerView
        patternRecyclerView.layoutManager = LinearLayoutManager(this)
        patternRecyclerView.setHasFixedSize(true)

        if(instructions != null){
            adapter = PatternInstructionsAdapter(instructions)
        }

        patternRecyclerView.adapter = adapter

        //setting image and title
        binding.patternNameTextView.text = singlePattern.name

        if(singlePattern.imageUrl != null && singlePattern.imageUrl!!.isNotEmpty()){
            //handle to get the image from a url
        } else {
            binding.patternImageView.setImageResource(R.drawable.logo_app)
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