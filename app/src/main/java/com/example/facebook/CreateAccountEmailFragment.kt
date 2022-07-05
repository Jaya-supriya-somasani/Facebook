package com.example.facebook

import com.example.facebook.databinding.FragmentCreateAccountMailBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccEmailViewModel

class CreateAccountEmailFragment:BaseFragment<FragmentCreateAccountMailBinding,CreateAccEmailViewModel>() {
    override fun getViewModel()=CreateAccEmailViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_mail
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}