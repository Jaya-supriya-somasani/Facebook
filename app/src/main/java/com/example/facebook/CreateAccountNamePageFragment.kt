package com.example.facebook

import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccUserNameViewModel
import com.example.facebook.viewmodels.CreateAccountViewModel

class CreateAccountNamePageFragment:
    BaseFragment<com.example.facebook.databinding.FragmentCreateAccountNamePagBinding, CreateAccUserNameViewModel>() {
    override fun getViewModel()=CreateAccUserNameViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_name_pag
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}