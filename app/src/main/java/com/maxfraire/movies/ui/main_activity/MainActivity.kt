package com.maxfraire.movies.ui.main_activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.color.MaterialColors
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.ActivityMainBinding
import com.maxfraire.movies.util.EventObserver
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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.navBar.setupWithNavController(navHostFragment.navController)

//        viewModel.translucentStatusBar.observe(this, EventObserver(onEventUnhandledContent = {
//            if (it) window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            else window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.statusBarColor = Color.TRANSPARENT
//            window.statusBarColor = MaterialColors.getColor(this, R.attr.colorPrimary, Color.BLACK)
//        }))
    }

}