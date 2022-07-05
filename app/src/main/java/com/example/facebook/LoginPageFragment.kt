package com.example.facebook

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.facebook.databinding.FragmentLoginPageBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.LoginPageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginPageFragment: BaseFragment<FragmentLoginPageBinding, LoginPageViewModel>() {

    private val args:LoginPageFragmentArgs by navArgs()
    override fun getViewModel()=LoginPageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_login_page
    }

    override fun initViews() {
        dataBinding.loginVM=viewModel

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.navigateToLoginScreenToHome.collect{
                withContext(Dispatchers.IO){
                    val action=LoginPageFragmentDirections.actionLoginPageFragmentToHomePageFragment()
                    findNavController().navigate(action)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.navigateToCreateAccount.collect{
                withContext(Dispatchers.IO){
                    val action=LoginPageFragmentDirections.navigationLoginToCreateAccJoinPage()
                    findNavController().navigate(action)
                }

            }
        }

    }
}