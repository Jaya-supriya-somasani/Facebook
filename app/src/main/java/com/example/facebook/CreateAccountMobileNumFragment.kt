package com.example.facebook

import com.example.facebook.databinding.FragmentCreateAccountPhnumBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccMobileNumViewModel

class CreateAccountMobileNumFragment:BaseFragment<FragmentCreateAccountPhnumBinding, CreateAccMobileNumViewModel>() {
    override fun getViewModel()=CreateAccMobileNumViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_phnum
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}