package com.example.facebook.fragment

import com.example.facebook.R
import com.example.facebook.databinding.FragmentUserFriendsBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.FriendsListViewModel

class FriendsPageFragment:BaseFragment<FragmentUserFriendsBinding, FriendsListViewModel>() {
    override fun getViewModel()=FriendsListViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_user_friends
    }

    override fun initViews() {
        dataBinding.userFriendsVM=viewModel
    }
}