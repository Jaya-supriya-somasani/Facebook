package com.example.facebook

import com.example.facebook.databinding.FragmentCreateAccountTermsPrivacyBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccPTermsViewModel
import com.example.facebook.viewmodels.CreateAccountViewModel

class CreateAccountTermsFragment:BaseFragment<FragmentCreateAccountTermsPrivacyBinding, CreateAccPTermsViewModel>() {
    override fun getViewModel()=CreateAccPTermsViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_terms_privacy
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}