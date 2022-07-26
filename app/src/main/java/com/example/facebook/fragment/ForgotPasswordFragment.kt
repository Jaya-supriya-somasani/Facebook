package com.example.facebook.fragment

import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentForgotPasswordBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.ForgotPasswordViewModel

class ForgotPasswordFragment:
    BaseFragment<FragmentForgotPasswordBinding,ForgotPasswordViewModel>() {

    override fun initViews() {
        dataBinding.btReset.setOnClickListener {
            // reset btn
        }
        dataBinding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        dataBinding.tvLogin.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun getViewModel(): Class<ForgotPasswordViewModel> = ForgotPasswordViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_forgot_password
    override fun getData() {
        //
    }
}