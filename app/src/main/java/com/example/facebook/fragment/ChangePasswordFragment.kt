package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentChangePasswordBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.ChangePasswordViewModel
import kotlinx.coroutines.flow.collectLatest

class ChangePasswordFragment:
    BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel>() {
    override fun getViewModel()=ChangePasswordViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_change_password
    }

    override fun initViews() {
        dataBinding.changePswdVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.restePasswordEvent.collectLatest {
                val action=ChangePasswordFragmentDirections.actionChangePasswordFragmentToLoginPageFragment()
                findNavController().navigate(action)
            }
        }
    }
}