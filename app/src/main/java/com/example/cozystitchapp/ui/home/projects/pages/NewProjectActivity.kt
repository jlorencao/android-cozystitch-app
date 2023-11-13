package com.example.cozystitchapp.ui.home.projects.pages

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.cozystitchapp.databinding.ActivityNewProjectBinding
import com.example.cozystitchapp.model.CrochetPattern
import com.example.cozystitchapp.model.Project
import com.example.cozystitchapp.ui.home.projects.viewmodel.ProjectsViewModel


class NewProjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewProjectBinding
    private val viewModel: ProjectsViewModel by viewModels()
    private lateinit var crochetPatternName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProjectBinding.inflate(layoutInflater)
        val view = binding.root

        //remove the title bar from the window
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(view)


        //set programmatically the height and width to MATCH PARENT so the pop up is larger
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)

        binding.closeDialogButton.setOnClickListener{
            finish()
        }
        crochetPatternName = ""
        binding.catalogPatternButton.setOnClickListener{
            //Dialog to chose the pattern from catalog
            openDialog(this)
        }

        binding.pdfPatternButton.setOnClickListener {
            //open the phone directory and chose a file

            //save the path on the pattern field

            //show the title of the file on the textView

        }

        Log.d("result_after_dialog", crochetPatternName)

        binding.saveProjectButton.setOnClickListener {
        //get the data from EditText
            val title = binding.projectNameEditText.text.toString()
            val type  = binding.projectTypeEditText.text.toString()
            val yarn = binding.yarnEditText.text.toString()
            val hook = binding.hookEditText.text.toString()
            val accessory = binding.accessoryEditText.text.toString()
            val crochetPatternName = binding.chosenPatternTextView.text.toString()

            Log.d("Save_button",crochetPatternName)


            //verify if all the fields important fields are filled
            if(title.isNotEmpty() && type.isNotEmpty() ){

                // create the project
                viewModel.saveNewProject(title, type, yarn, hook, accessory, crochetPatternName)

            }
        //close the window
            viewModel.getIsSavedLiveData().observe(this) { dataSaved ->
                    if (dataSaved != null && dataSaved) {
                        finish()
                    }

                }
        }
    }
    fun openDialog(context: Context){
        // Retrieve pattern titles from SharedPreferences
        val sharedPreferences = getSharedPreferences("patterns", Context.MODE_PRIVATE)
        val patternTitleSet = sharedPreferences.getStringSet("patterns", null)
        val patternTitles = patternTitleSet?.toList() ?: emptyList()
        val patternArray = patternTitles.toTypedArray()
        var patternName = ""

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setTitle("Pattern List")
            .setSingleChoiceItems(
                patternArray, 0
            ) { dialog, which ->
                patternName = patternArray[which].toString()
                Log.d("dialog_result", patternName)
            }
            .setPositiveButton("Save") { dialog, which ->
                // Do something.
                binding.chosenPatternTextView.text = patternName
                Log.d("dialog_result", patternName)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}
