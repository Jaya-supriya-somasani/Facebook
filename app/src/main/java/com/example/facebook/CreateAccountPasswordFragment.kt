package com.example.facebook

import com.example.facebook.databinding.FragmentCreateAccountPasswordBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccPasswordViewModel
import com.example.facebook.viewmodels.CreateAccountViewModel

class CreateAccountPasswordFragment:BaseFragment<FragmentCreateAccountPasswordBinding,CreateAccPasswordViewModel>() {
    override fun getViewModel()=CreateAccPasswordViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_password
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}