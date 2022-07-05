package com.example.facebook

import com.example.facebook.databinding.FragmentCreateAccountBdayPageBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccBdayViewModel

class CreateAccountBDayFragment:BaseFragment<FragmentCreateAccountBdayPageBinding, CreateAccBdayViewModel>() {
    override fun getViewModel()=CreateAccBdayViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_bday_page
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}