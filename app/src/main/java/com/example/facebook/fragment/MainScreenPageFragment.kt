package com.example.facebook.fragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.adapter.PostAdapter
import com.example.facebook.adapter.SuggestFriendsAdapter
import com.example.facebook.api.request.SuggestFriendResponse
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.databinding.FragmentMainScreenPageBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.HomeMainViewModel
import kotlinx.coroutines.flow.collectLatest


class MainScreenPageFragment : BaseFragment<FragmentMainScreenPageBinding, HomeMainViewModel>() {

    private val suggestFriendsAdapter = SuggestFriendsAdapter(::onAddClicked, ::onRemoveClicked)

    private val postAdapter = PostAdapter(
        onDeletePostClicked = ::onDeleteClicked,
        onPostLiked = ::onPostLiked
    )

    private fun onRemoveClicked(item: SuggestFriendResponse) {
        //   Toast.makeText(requireContext(), "clicked on remove button", Toast.LENGTH_SHORT).show()
    }

    private fun onAddClicked(item: SuggestFriendResponse) {
        viewModel.addFriend(item)

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
        dataBinding.viewModel = viewModel
        initData()



        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenCreated {
            appDataStore.userIdFlow.collectLatest {
                viewModel.userId.value = it
            }
        }

        dataBinding.recyclerViewFriends.adapter = suggestFriendsAdapter

        dataBinding.recyclerViewPosts.adapter = postAdapter




        dataBinding.layoutCreatePost.searchView.setOnClickListener {
            val action =
                MainScreenPageFragmentDirections.actionHomeMainFragmentToCreatePostFragment2()
            findNavController().navigate(action)
        }
    }


    private fun initData() {
        lifecycleScope.launchWhenResumed {
            viewModel.getSuggestFriends()
        }
        lifecycleScope.launchWhenResumed {
            viewModel.getPosts()
        }
        lifecycleScope.launchWhenResumed {
            viewModel.postDetailsStateFlow.collect {

                postAdapter.submitList(it)


                Log.d("List", "initData: $it")
//                postAdapter.postDetailsNotify(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.suggestFriendsList.collectLatest {
                suggestFriendsAdapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

}