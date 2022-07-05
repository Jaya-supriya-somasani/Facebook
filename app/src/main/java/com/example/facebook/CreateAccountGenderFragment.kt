package com.example.facebook

import com.example.facebook.databinding.FragmentCreateAccountGenderPageBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccGenderViewModel

class CreateAccountGenderFragment:BaseFragment<FragmentCreateAccountGenderPageBinding, CreateAccGenderViewModel>() {
    override fun getViewModel()=CreateAccGenderViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_gender_page
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}