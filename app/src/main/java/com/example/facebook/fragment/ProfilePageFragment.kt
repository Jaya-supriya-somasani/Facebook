package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.facebook.R
import com.example.facebook.datastore.AppDataStore
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
        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenResumed {
            appDataStore.userIdFlow.collectLatest {
                viewModel.getProfileData(it)
            }
        }
        dataBinding.changePasswordBtn.setOnClickListener {
            val action =
                ProfilePageFragmentDirections.actionProfilePageFragment2ToChangePasswordFragment2()
            view?.let { it1 -> Navigation.findNavController(it1) }?.navigate(action)
        }
        lifecycleScope.launchWhenResumed {
            viewModel.userDetailsStateFlow.collectLatest {
                dataBinding.data = it
            }
        }
    }
}