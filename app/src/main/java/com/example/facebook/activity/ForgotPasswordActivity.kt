package com.example.facebook.activity

import com.example.facebook.R
import com.example.facebook.databinding.ActivityForgotPasswordBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.ForgotPasswordViewModel

class ForgotPasswordActivity :
    BaseActivity<ActivityForgotPasswordBinding, ForgotPasswordViewModel>() {
    override fun setupViews() {
        dataBinding.btReset.setOnClickListener {
            // reset btn
        }
        dataBinding.ivBack.setOnClickListener {
            onBackPressed()
        }
        dataBinding.tvLogin.setOnClickListener {
            onBackPressed()
        }
    }

    override fun getViewModel(): Class<ForgotPasswordViewModel> =
        ForgotPasswordViewModel::class.java

    override fun getResourceId(): Int = R.layout.activity_forgot_password
}