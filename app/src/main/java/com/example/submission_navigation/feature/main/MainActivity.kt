package com.example.submission_navigation.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.submission_navigation.R
import com.example.submission_navigation.databinding.ActivityMainBinding
import com.example.submission_navigation.feature.details.DetailFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val navigateToDetail = intent.getBooleanExtra("navigate_to_detail", false)
        if (navigateToDetail) {
            val login = intent.getStringExtra("login")
            navigateToDetailFragment(login)
        }
    }

    private fun navigateToDetailFragment(login: String?) {
        val fragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putString("login", login)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}