package com.example.facebook.activity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.facebook.R
import com.example.facebook.databinding.ActivityMainBinding
import com.example.facebook.fragment.MainScreenPageFragmentDirections
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.HomeActivityViewModel

class MainActivity : BaseActivity<ActivityMainBinding, HomeActivityViewModel>() {
    private val navHostFragment: NavHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment

    private val navController: NavController
        get() = navHostFragment.navController

    override fun setupViews() {
        dataBinding.bottomNavigation.setupWithNavController(navController)
        dataBinding.bottomNavigation.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            navController.navigateUp()
            when (item.itemId) {
                R.id.friends_nav_btn -> {
                    val action =
                        MainScreenPageFragmentDirections.actionHomeMainFragmentToFriendsPageFragment()
                    navController.navigate(action)
                }
                R.id.profile_nav_btn -> {
                    val action =
                        MainScreenPageFragmentDirections.actionHomeMainFragmentToProfilePageFragment()
                    navController.navigate(action)
                }
                R.id.create_post_nav_btn -> {
                    val action =
                        MainScreenPageFragmentDirections.actionHomeMainFragmentToCreatePostFragment2()
                    navController.navigate(action)

                }
            }
            true
        }

    }

    override fun getViewModel() = HomeActivityViewModel::class.java


    override fun getResourceId(): Int = R.layout.activity_main

}