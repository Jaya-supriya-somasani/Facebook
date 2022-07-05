package com.example.facebook

import com.example.facebook.databinding.FragmentCreateAccountJoinPgBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccJoinPageViewModel
import com.example.facebook.viewmodels.CreateAccountViewModel

class CreateAccJoinPageFragment:BaseFragment<FragmentCreateAccountJoinPgBinding,CreateAccJoinPageViewModel>() {

    override fun getViewModel()=CreateAccJoinPageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_join_pg
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}