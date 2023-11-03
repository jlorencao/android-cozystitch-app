package com.example.cozystitchapp.ui.home


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.cozystitchapp.R
import com.example.cozystitchapp.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toolbar = findViewById<Toolbar>(R.id.topBarView)
        setSupportActionBar(toolbar)



        //start with HomePageFragment
        openFragment(HomePageFragment())


        //handle the action button from toolbar to create a new project
        binding.topBarView.addNewProjectIcon.setOnClickListener {
            Log.d("ACTION_BUTTON","button pressed")
        }


        //handling the Bottom Navigation
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homePageIcon -> {
                    openFragment(HomePageFragment())
                    binding.topBarView.topBarTitle.text = getString(R.string.home_icon_label)

                    true
                }
                R.id.projectsPageIcon -> {
                    openFragment(ProjectsPageFragment())
                    binding.topBarView.topBarTitle.text = getString(R.string.projects_label)

                    true
                }
                R.id.patternCatalogIcon -> {
                    openFragment(PatternsPageFragment())
                    binding.topBarView.topBarTitle.text = getString(R.string.patterns_catalog_label)

                    true
                }
                R.id.profilePageIcon -> {
                    openFragment(ProfilePageFragment())
                    binding.topBarView.topBarTitle.text = getString(R.string.profile_label)

                    true
                }
                else -> false
            }
        }
    }
        private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerView, fragment)
            .commit()
    }

}