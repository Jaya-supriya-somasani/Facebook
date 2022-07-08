package com.example.facebook.fragment

import androidx.appcompat.app.ActionBar
import com.example.facebook.R
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.ProfilePageViewModel

class ProfilePageFragment :
    BaseFragment<com.example.facebook.databinding.FragmentProfilePageBinding, ProfilePageViewModel>() {
    override fun getViewModel() = ProfilePageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_profile_page
    }

    override fun initViews() {
        dataBinding.profileVM = viewModel

    }
}