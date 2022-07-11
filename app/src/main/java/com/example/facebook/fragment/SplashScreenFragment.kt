package com.example.facebook.fragment

import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentSplashScreenBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.SplashScreenViewModel


class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>() {
    private var status = false
    override fun getViewModel(): Class<SplashScreenViewModel> = SplashScreenViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_splash_screen

    override fun initViews() {
        Handler().postDelayed({
            val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
            findNavController().navigate(action)
        }, (2000).toLong())
    }
}
