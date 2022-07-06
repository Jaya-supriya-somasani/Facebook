package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccUserNameViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountNamePageFragment:
    BaseFragment<com.example.facebook.databinding.FragmentCreateAccountNamePagBinding, CreateAccUserNameViewModel>() {
    override fun getViewModel()=CreateAccUserNameViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_name_pag
    }

    override fun initViews() {
        dataBinding.createUserVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createUserNameEvent.collectLatest {
                val action= CreateAccountNamePageFragmentDirections.navCreateAccUserNameFragmentToCreateAccBDayFragment()
                findNavController().navigate(action)
            }
        }
    }
}