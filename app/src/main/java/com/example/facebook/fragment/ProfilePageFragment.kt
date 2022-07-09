package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.ProfilePageViewModel
import kotlinx.coroutines.flow.collectLatest

class ProfilePageFragment :
    BaseFragment<com.example.facebook.databinding.FragmentProfilePageBinding, ProfilePageViewModel>() {
    override fun getViewModel() = ProfilePageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_profile_page
    }

    override fun initViews() {
        dataBinding.changePasswordBtn.setOnClickListener {
            val action =
                ProfilePageFragmentDirections.actionProfilePageFragment2ToChangePasswordFragment2()
            findNavController().navigate(action)
        }
        lifecycleScope.launchWhenResumed {
            viewModel.userDetailsStateFlow.collectLatest {
                dataBinding.data = it
            }
        }
    }
}