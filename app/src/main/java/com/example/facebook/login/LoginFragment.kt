package com.example.facebook.login

import android.content.Intent
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.activity.MainActivity
import com.example.facebook.databinding.FragmentLoginBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginPageViewModel>() {

    override fun getViewModel() = LoginPageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_login
    }


    override fun initViews() {
        dataBinding.loginVM = viewModel
        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenResumed {
            appDataStore.setLaunchImmediate(false)
        }
        lifecycleScope.launchWhenResumed {
            viewModel.loginEvent.collectLatest { loginStatus ->
                activity?.let { fragmentActivity ->
                    appDataStore.saveUserId(loginStatus.userId)
                    appDataStore.saveUserName(loginStatus.userName)
                    appDataStore.setLoginStatus(true)

                    val intent = Intent(fragmentActivity, MainActivity::class.java)
                    fragmentActivity.startActivity(intent)
                    fragmentActivity.finish()
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

//        val userId=arguments?.let {
//            it.getSerializable("userId") as userId
//
//        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}