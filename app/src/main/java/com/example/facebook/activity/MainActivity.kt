package com.example.facebook.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.facebook.R
import com.example.facebook.databinding.ActivityMainBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.fragment.MainScreenPageFragmentDirections
import com.example.facebook.util.BaseActivity
import com.example.facebook.util.findNavController
import com.example.facebook.viewmodels.HomeActivityViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : BaseActivity<ActivityMainBinding, HomeActivityViewModel>() {
    private val navHostFragment: NavHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment

    private val navController: NavController
        get() = navHostFragment.navController

    override fun setupViews() {
        val appDataStore = AppDataStore(this)
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
                R.id.logOutBtn -> {
                    lifecycleScope.launchWhenResumed {
                        appDataStore.userIdFlow.collectLatest {
                            viewModel.logout(it)
                        }
                    }
                }
            }
            true
        }
        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.loginScreenEvent.collectLatest {
//                val intent = Intent(this@MainActivity, HomeActivity::class.java)
//                intent.putExtra("screen", "Login")
//                startActivity(intent)
//                finish()
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("app://my_facebook/login")
                }
                startActivity(intent)
                finish()
            }
        }


    }

    override fun getViewModel() = HomeActivityViewModel::class.java


    override fun getResourceId(): Int = R.layout.activity_main

}