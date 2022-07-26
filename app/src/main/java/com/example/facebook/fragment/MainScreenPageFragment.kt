package com.example.facebook.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.adapter.DiffAdapter
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

    private val postAdapter = DiffAdapter(
        onDeletePostClicked = ::onDeleteClicked,
        onPostLiked = ::onPostLiked
    )

    private fun onRemoveClicked(item: SuggestFriendResponse) {
        Toast.makeText(requireContext(), "clicked on remove button", Toast.LENGTH_SHORT).show()
    }

    private fun onAddClicked(item: SuggestFriendResponse,position: Int) {
        viewModel.addFriend(item,position)

    }

    private fun onPostLiked(item: PostsResponsesItem, position: Int) {
        viewModel.onLikeClicked(item, position)
    }

    private fun onDeleteClicked(item: PostsResponsesItem, position: Int) {
        viewModel.onDeleteClicked(item, position)
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
            viewModel.likePostChangeEvent.collectLatest {
                postAdapter.notifyItemChanged(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.userPostFlow.collectLatest {

            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.addFriendEvent.collectLatest {
                // Removed item after adding the suggested friend
                suggestFriendsAdapter.notifyItemRemoved(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.deletePostChangeEvent.collectLatest {
                postAdapter.notifyItemRemoved(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.postDetailsMutableState.collectLatest {
                postAdapter.setData(it)
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

    override fun getData() {
    lifecycleScope.launchWhenResumed {
        viewModel.getPosts()
        viewModel.getSuggestFriends()
    }
    }

}