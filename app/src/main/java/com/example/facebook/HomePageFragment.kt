package com.example.facebook

import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.HomePageViewModel

class HomePageFragment:
    BaseFragment<com.example.facebook.databinding.FragmentHomePageBinding, HomePageViewModel>() {
    override fun getViewModel()=HomePageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_home_page
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}