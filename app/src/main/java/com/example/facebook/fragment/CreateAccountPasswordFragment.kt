package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountPasswordBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccPasswordViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountPasswordFragment:BaseFragment<FragmentCreateAccountPasswordBinding,CreateAccPasswordViewModel>() {
    override fun getViewModel()=CreateAccPasswordViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_password
    }

    override fun initViews() {
        dataBinding.userPswdVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.userPswdEvent.collectLatest {
                val action= CreateAccountPasswordFragmentDirections.navCreateAccPswdFragmentToCreateAccEmailFragment()
                findNavController().navigate(action)
            }
        }
    }

}