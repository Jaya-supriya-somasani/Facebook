package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountJoinPgBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccJoinPageViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccJoinPageFragment:BaseFragment<FragmentCreateAccountJoinPgBinding,CreateAccJoinPageViewModel>() {

    override fun getViewModel()=CreateAccJoinPageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_join_pg
    }

    override fun initViews() {
        dataBinding.createJoinVM=viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createJoinEvent.collectLatest {
                val action=CreateAccJoinPageFragmentDirections.navCreateAccJoinPageFragmentToCreateUserNamePageFragment()
                findNavController().navigate(action)
            }
        }

    }
}