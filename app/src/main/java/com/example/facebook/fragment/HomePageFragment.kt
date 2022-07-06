package com.example.facebook.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.facebook.R
import com.example.facebook.home.HomeFragment
import com.example.facebook.home.HomeMainFragment
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.HomePageViewModel

class HomePageFragment :
    BaseFragment<com.example.facebook.databinding.FragmentHomePageBinding, HomePageViewModel>() {
    override fun getViewModel() = HomePageViewModel::class.java


    override fun getResourceId(): Int {
        return R.layout.fragment_home_page
    }


    override fun initViews() {
        viewModel = ViewModelProvider(this)[getViewModel()]
        dataBinding.homeViewModel = viewModel
        dataBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_nav_btn ->{
                   HomeMainFragment()
                }
            }
            true
        }
    }
}