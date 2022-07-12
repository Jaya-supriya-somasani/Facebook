package com.example.facebook.fragment

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.activity.MainActivity
import com.example.facebook.api.response.LoginStatus
import com.example.facebook.databinding.FragmentLoginBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.viewmodels.LoginPageViewModel
import com.example.facebook.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginPageViewModel>() {

    override fun getViewModel() = LoginPageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_login
    }


    override fun initViews() {
        val appDataStore = AppDataStore(requireContext())
        dataBinding.loginVM = viewModel
        lifecycleScope.launchWhenResumed {
            viewModel.loginEvent.collectLatest {
                appDataStore.saveToDataStore((LoginStatus(it.userId, it.userId, it.loginStatus)))
                activity?.let { move ->
                    val intent = Intent(move, MainActivity::class.java)
                    move.startActivity(intent)
                }

            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterAccountActivity()
                findNavController().navigate(action)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.forgotPasswordEvent.collectLatest {
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                findNavController().navigate(action)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}