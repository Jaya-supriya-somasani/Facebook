package com.example.facebook.activity

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("Are you sure ?").setTitle("Warning")
                        .setNegativeButton("Cancel"
                        ) { dialog, which ->

                        }
                        .setPositiveButton("Ok"
                        ) { dialog, which ->
                            lifecycleScope.launchWhenResumed {
                                appDataStore.userIdFlow.collectLatest {
                                    viewModel.logout(it)
                                }
                            }
                        }
                    val dialog = builder.create()
                    dialog.show()
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
                Log.d("Logout", "status: $it")
//                val intent = Intent(Intent.ACTION_VIEW).apply {
//                    data = Uri.parse("app://my_facebook/login")
//                }
                appDataStore.setLoginStatus(false)
                appDataStore.setLaunchImmediate(true)
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }
        }


    }


    override fun getViewModel() = HomeActivityViewModel::class.java


    override fun getResourceId(): Int = R.layout.activity_main

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)


}