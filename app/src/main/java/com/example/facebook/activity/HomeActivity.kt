package com.example.facebook.activity

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.facebook.DaggerApplicationComponent
import com.example.facebook.R
import com.example.facebook.databinding.ActivityHomeBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.HomeActivityViewModel
import kotlinx.coroutines.flow.collectLatest


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    override fun getViewModel(): Class<HomeActivityViewModel> = HomeActivityViewModel::class.java

    override fun getResourceId(): Int = R.layout.activity_home

    override fun setupViews() {

        val applicationComponent=DaggerApplicationComponent.builder().build()
        applicationComponent.inject(this)

        lifecycleScope.launchWhenCreated {
            viewModel.navigateNextScreenEvent.collectLatest {
                moveToScreen(it)
            }
        }
    }
    private fun moveToScreen(status: Boolean) {
        if (status) {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        } else Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

    }

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

}