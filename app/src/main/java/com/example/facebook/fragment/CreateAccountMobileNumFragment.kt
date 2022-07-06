package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountPhnumBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccMobileNumViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountMobileNumFragment:BaseFragment<FragmentCreateAccountPhnumBinding, CreateAccMobileNumViewModel>() {
    override fun getViewModel()=CreateAccMobileNumViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_phnum
    }

    override fun initViews() {
        dataBinding.userPhNumberVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createPhNumEent.collectLatest {
                val action= CreateAccountMobileNumFragmentDirections.navCreateAccPhNumFragmentToCreateAccPswdFragment()
                findNavController().navigate(action)
            }
        }
    }
}