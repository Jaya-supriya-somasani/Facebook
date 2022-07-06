package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountTermsPrivacyBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccPTermsViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountTermsFragment:BaseFragment<FragmentCreateAccountTermsPrivacyBinding, CreateAccPTermsViewModel>() {
    override fun getViewModel()=CreateAccPTermsViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_terms_privacy
    }

    override fun initViews() {
        dataBinding.termsVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.termsConditionsEvent.collectLatest {
                val action= CreateAccountTermsFragmentDirections.actionCreateAccountTermsFragmentToLoginPageFragment()
                findNavController().navigate(action)
            }
        }
    }
}