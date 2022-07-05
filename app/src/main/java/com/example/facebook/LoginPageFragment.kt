package com.example.facebook

import com.example.facebook.databinding.FragmentLoginPageBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.LoginPageViewModel

class LoginPageFragment: BaseFragment<FragmentLoginPageBinding, LoginPageViewModel>() {
    override fun getViewModel(): Class<LoginPageViewModel> {
        TODO("Not yet implemented")
    }

    override fun getResourceId(): Int {
        return R.layout.fragment_login_page
    }

    override fun initViews() {

    }
}