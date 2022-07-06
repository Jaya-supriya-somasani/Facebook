package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountGenderPageBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccGenderViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountGenderFragment:BaseFragment<FragmentCreateAccountGenderPageBinding, CreateAccGenderViewModel>() {
    override fun getViewModel()=CreateAccGenderViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_gender_page
    }

    override fun initViews() {
        dataBinding.userGenderVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.userGenderEvent.collectLatest {
                val action= CreateAccountGenderFragmentDirections.navCreateAccGenderFragmentToCreateAccPhnumFragment()
                findNavController().navigate(action)
            }
        }
    }
}