package com.example.facebook.activity

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.facebook.R
import com.example.facebook.databinding.FragmentLoginPageBinding
import com.example.facebook.fragment.login.LoginPageViewModel
import com.example.facebook.util.BaseActivity
import kotlinx.coroutines.flow.collectLatest


class MainActivity : BaseActivity<FragmentLoginPageBinding, LoginPageViewModel>() {

    override fun getViewModel() = LoginPageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_login_page
    }


    override fun setupViews() {
        dataBinding.loginVM = viewModel
        lifecycleScope.launchWhenResumed {
            viewModel.loginEvent.collectLatest {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                //change the binding in activity
                startActivity(Intent(this@MainActivity, RegisterAccounActivity::class.java))
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.forgotPasswordEvent.collectLatest {
                startActivity(Intent(this@MainActivity, ForgotPasswordActivity::class.java))

            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}