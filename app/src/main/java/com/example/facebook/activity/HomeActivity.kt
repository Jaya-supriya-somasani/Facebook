package com.example.facebook.activity

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.facebook.R
import com.example.facebook.databinding.ActivityHomeBinding
import com.example.facebook.fragment.HomeMainFragmentDirections
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.HomeActivityViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    private val navHostFragment: NavHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment
    private val navController: NavController
        get() = navHostFragment.navController

    override fun setupViews() {
        dataBinding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            supportActionBar?.hide()
        }
        dataBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_nav_btn -> {
                    Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()

                }
                R.id.friends_nav_btn -> {
                    Toast.makeText(this, "friends", Toast.LENGTH_SHORT).show()
                }
                R.id.profile_nav_btn -> {
                    val action =
                        HomeMainFragmentDirections.actionHomeMainFragmentToProfilePageFragment()
                    navController.navigate(action)
                }
                R.id.create_post_nav_btn -> {
                    val action =
                        HomeMainFragmentDirections.actionHomeMainFragmentToCreatePostFragment2()
                    navController.navigate(action)

                }
            }
            true
        }

    }
//
//    fun Activity.navigate(directions: NavDirections) {
//        val currentDestination =
//            (navController.currentDestination as? FragmentNavigator.Destination)?.className
//                ?: (navController.currentDestination as? DialogFragmentNavigator.Destination)?.className
//        if (currentDestination == this.javaClass.name) {
//            Log.e("TAG", "navigate: $currentDestination")
//            navController.navigate(directions)
//        }
//    }

    override fun getViewModel() = HomeActivityViewModel::class.java


    override fun getResourceId(): Int = R.layout.activity_home

}