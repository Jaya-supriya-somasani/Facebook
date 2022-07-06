package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentLoginPageBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.LoginPageViewModel
import kotlinx.coroutines.flow.collectLatest

class LoginPageFragment : BaseFragment<FragmentLoginPageBinding, LoginPageViewModel>() {

    //private val args:LoginPageFragmentArgs by navArgs()
    override fun getViewModel() = LoginPageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_login_page
    }

    override fun initViews() {
        dataBinding.loginVM = viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.loginEvent.collectLatest {
                loginChecking()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                val action= LoginPageFragmentDirections.navigationLoginToCreateAccJoinPage()
                findNavController().navigate(action)
            }
        }
    }

    private fun loginChecking() {
        if (dataBinding.phEmailEdt.text.toString().isEmpty()) {
            dataBinding.tilPhnOrEmail.error = "Please Enter Email or Phone Number"
        } else if (dataBinding.etPassword.text.toString().isEmpty()) {
            dataBinding.tilPassword.error = "Please Enter Password"
        } else {
            dataBinding.tilPhnOrEmail.error = ""
            dataBinding.tilPassword.error = ""
            login()
        }

    }

    private fun login() {
        val action = LoginPageFragmentDirections.actionLoginPageFragmentToHomeFragment2()
        findNavController().navigate(action)
    }
}