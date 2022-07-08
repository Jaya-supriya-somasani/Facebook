package com.example.facebook.activity

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.facebook.R
import com.example.facebook.databinding.ActivityMainBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun getViewModel() = MainActivityViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.activity_main
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
                startActivity(Intent(this@MainActivity, CreateAccountActivity::class.java))
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.forgotPasswordEvent.collectLatest {
                startActivity(Intent(this@MainActivity, ChangePasswordActivity::class.java))

            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}