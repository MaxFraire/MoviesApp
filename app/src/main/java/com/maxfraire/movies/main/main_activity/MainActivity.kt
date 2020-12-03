package com.maxfraire.movies.main.main_activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.navBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_movies -> Toast.makeText(this, "movies", Toast.LENGTH_SHORT).show()
                R.id.nav_tv_shows -> Toast.makeText(this, "shows", Toast.LENGTH_SHORT).show()
                R.id.nav_favorites -> Toast.makeText(this, "fav", Toast.LENGTH_SHORT).show()
                R.id.nav_search -> Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

}