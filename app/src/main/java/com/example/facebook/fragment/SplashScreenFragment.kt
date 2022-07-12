package com.example.facebook.fragment

import android.content.Intent
import android.os.Handler
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.activity.MainActivity
import com.example.facebook.databinding.FragmentSplashScreenBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.SplashScreenViewModel
import kotlinx.coroutines.flow.collectLatest


class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>() {
    override fun getViewModel(): Class<SplashScreenViewModel> = SplashScreenViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_splash_screen

    override fun initViews() {
        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenCreated {
            appDataStore.userLoggedStatusFlow.collectLatest {
                Handler().postDelayed({
                    moveScreen(it)
                }, 1000)
            }
        }

    }

    private fun moveScreen(status: Boolean) {
        if (status) {
            activity?.let { move ->
                val intent = Intent(move, MainActivity::class.java)
                move.startActivity(intent)
            }
        } else {
            val action =
                SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}
