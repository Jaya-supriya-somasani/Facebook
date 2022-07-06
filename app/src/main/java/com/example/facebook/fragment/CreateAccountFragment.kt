package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccountViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountFragment:BaseFragment<FragmentCreateAccountBinding,CreateAccountViewModel>() {
    override fun getViewModel()=CreateAccountViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account
    }

    override fun initViews() {
        dataBinding.createAccViewModel=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                val action= CreateAccountFragmentDirections.actionCreateAccountFragmentToProfilePageFragment2()
                findNavController().navigate(action)
            }
        }
    }
}