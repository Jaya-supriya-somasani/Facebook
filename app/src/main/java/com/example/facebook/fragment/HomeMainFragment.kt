package com.example.facebook.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.adapter.FriendsListAdapter
import com.example.facebook.adapter.PostAdapter
import com.example.facebook.api.request.FriendsListResponse
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.databinding.FragmentHomeMainBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.HomePageViewModel
import kotlinx.coroutines.flow.collectLatest

class HomeMainFragment : BaseFragment<FragmentHomeMainBinding, HomePageViewModel>() {

    val friendsListAdapter = FriendsListAdapter(::onAddClicked, ::onRemoveClicked)
    val postAdapter = PostAdapter()

    private fun onRemoveClicked(item: FriendsListResponse) {
        Toast.makeText(requireContext(), "clicked on remove button", Toast.LENGTH_SHORT).show()
    }

    private fun onAddClicked(item: FriendsListResponse) {
        Toast.makeText(requireContext(), "clicked on add button", Toast.LENGTH_SHORT).show()

    }

    override fun getViewModel(): Class<HomePageViewModel> = HomePageViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_home_main

    override fun initViews() {
       // initData()
        val data = listOf(
            FriendsListResponse("tarun1", "lanka", ""),
            FriendsListResponse("tarun2", "lanka", ""),
            FriendsListResponse("tarun3", "lanka", "")
        )
//        val postData = listOf(
//            PostsResponsesItem("ram", "1", "austrlia"),
//            PostsResponsesItem("athira", "2", "kerala"),
//            PostsResponsesItem("lovely", "3", "chennai"),
//        )
        dataBinding.recyclerViewFriends.adapter = friendsListAdapter
        dataBinding.recyclerViewPosts.adapter = postAdapter
        friendsListAdapter.submitList(data)
//        postAdapter.submitList(postData)
        dataBinding.layoutCreatePost.searchView.setOnClickListener {
            val action = HomeMainFragmentDirections.actionHomeMainFragmentToCreatePostFragment2()
            findNavController().navigate(action)
        }
    }

    private fun initData() {
        lifecycleScope.launchWhenResumed {
            viewModel.postDetailsStateFlow.collectLatest {
                postAdapter.submitList(it)
            }
        }
    }

}