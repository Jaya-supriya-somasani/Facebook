package com.example.facebook.activity

import com.example.facebook.R
import com.example.facebook.databinding.ActivityHomeBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.HomeActivityViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    override fun setupViews() {

    }

    override fun getViewModel(): Class<HomeActivityViewModel> = HomeActivityViewModel::class.java

    override fun getResourceId(): Int = R.layout.activity_home
}