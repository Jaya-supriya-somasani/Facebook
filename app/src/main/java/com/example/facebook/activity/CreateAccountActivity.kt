package com.example.facebook.activity


import com.example.facebook.R
import com.example.facebook.databinding.ActivityChangePasswordBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.ChangePasswordViewModel

class CreateAccountActivity :
    BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel>() {
    override fun setupViews() {
        dataBinding.changePswdVM = viewModel


    }

    override fun getViewModel(): Class<ChangePasswordViewModel> =
        ChangePasswordViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.activity_change_password
    }
}

