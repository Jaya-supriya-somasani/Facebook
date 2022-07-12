package com.example.facebook.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.adapter.FriendsListAdapter
import com.example.facebook.adapter.PostAdapter
import com.example.facebook.api.request.FriendsListResponse
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.databinding.FragmentMainScreenPageBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.HomeMainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainScreenPageFragment : BaseFragment<FragmentMainScreenPageBinding, HomeMainViewModel>() {

    private val friendsListAdapter = FriendsListAdapter(::onAddClicked, ::onRemoveClicked)
    private val postAdapter = PostAdapter(::onDeleteClicked, ::onPostLiked)

    private fun onRemoveClicked(item: FriendsListResponse) {
        Toast.makeText(requireContext(), "clicked on remove button", Toast.LENGTH_SHORT).show()
    }

    private fun onAddClicked(item: FriendsListResponse) {
        Toast.makeText(requireContext(), "clicked on add button", Toast.LENGTH_SHORT).show()

    }

    private fun onPostLiked(item: PostsResponsesItem) {
        viewModel.onLikeClicked(item)
    }

    private fun onDeleteClicked(item: PostsResponsesItem) {
        viewModel.onDeleteClicked(item)
    }

    override fun getViewModel(): Class<HomeMainViewModel> = HomeMainViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_main_screen_page

    override fun initViews() {
        initData()
        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenResumed {
            appDataStore.userIdFlow.collectLatest {
                viewModel.userId.value = it
                viewModel.getPosts()
            }
        }
        val data = listOf(
            FriendsListResponse("tarun1", "lanka", ""),
            FriendsListResponse("tarun2", "lanka", ""),
            FriendsListResponse("tarun3", "lanka", "")
        )
        dataBinding.recyclerViewFriends.adapter = friendsListAdapter
        dataBinding.recyclerViewPosts.adapter = postAdapter
        friendsListAdapter.submitList(data)
        dataBinding.layoutCreatePost.searchView.setOnClickListener {
            val action =
                MainScreenPageFragmentDirections.actionHomeMainFragmentToCreatePostFragment2()
            findNavController().navigate(action)
        }
    }

    private fun initData() {
        // viewModel.getData()
        lifecycleScope.launchWhenResumed {
            viewModel.postDetailsStateFlow.collectLatest {
                postAdapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

}