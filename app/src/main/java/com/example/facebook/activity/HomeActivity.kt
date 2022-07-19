package com.example.facebook.activity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.facebook.R
import com.example.facebook.databinding.ActivityHomeBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.HomeActivityViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
        //finish()
    }
    override fun setupViews() {

    }

    override fun getViewModel(): Class<HomeActivityViewModel> = HomeActivityViewModel::class.java

    override fun getResourceId(): Int = R.layout.activity_home
}