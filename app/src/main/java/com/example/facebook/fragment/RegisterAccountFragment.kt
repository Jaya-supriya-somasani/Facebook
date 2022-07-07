package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentRegisterAccountBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.RegisterAccountViewModel
import kotlinx.coroutines.flow.collectLatest

class RegisterAccountFragment:BaseFragment<FragmentRegisterAccountBinding,RegisterAccountViewModel>() {
    override fun getViewModel()= RegisterAccountViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_register_account
    }

    override fun initViews() {
        dataBinding.createAccViewModel=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                val action= RegisterAccountFragmentDirections.actionCreateAccountFragmentToProfilePageFragment2()
                findNavController().navigate(action)
            }
        }
    }
}