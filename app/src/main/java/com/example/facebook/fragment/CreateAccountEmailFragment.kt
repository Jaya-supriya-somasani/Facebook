package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountMailBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccEmailViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountEmailFragment:BaseFragment<FragmentCreateAccountMailBinding,CreateAccEmailViewModel>() {
    override fun getViewModel()=CreateAccEmailViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_mail
    }

    override fun initViews() {
        dataBinding.userEmailVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.userEmailEvent.collectLatest {
                val action=CreateAccountEmailFragmentDirections.navCreateAccEmailFragmentToCreateAccTermsFragment()
                findNavController().navigate(action)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.userEmailSkipEvent.collectLatest {
                val action=CreateAccountEmailFragmentDirections.navCreateAccEmailFragmentToCreateAccTermsFragment()
                findNavController().navigate(action)
            }
        }
    }
}