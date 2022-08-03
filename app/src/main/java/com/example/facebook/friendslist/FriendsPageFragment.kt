package com.example.facebook.friendslist

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.DaggerApplicationComponent
import com.example.facebook.R
import com.example.facebook.adapter.AddFriendsListAdapter
import com.example.facebook.api.request.FriendDetailResponse
import com.example.facebook.databinding.FragmentUserFriendsBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class FriendsPageFragment : BaseFragment<FragmentUserFriendsBinding, FriendsListViewModel>() {
    override fun getViewModel() = FriendsListViewModel::class.java
    private val adapter = AddFriendsListAdapter(
        onDeleteFriend = ::onRemoveFriendClicked
    )
    private lateinit var friendsList: List<FriendDetailResponse>
    lateinit var userId: String
    override fun getResourceId(): Int {
        return R.layout.fragment_user_friends
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initViews() {
        val applicationComponent=DaggerApplicationComponent.builder().build()
        applicationComponent.inject(this)
        dataBinding.userFriendsVM = viewModel
        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenResumed {
            viewModel.friendsList.collectLatest {
                friendsList = it
                adapter.submitList(friendsList)
            }
        }
        lifecycleScope.launchWhenCreated {
            appDataStore.userIdFlow.collectLatest {
                userId = it
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.getFriendsList(userId)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.totalFriendsList.collectLatest {
                if (it == 0) {
                    dataBinding.totalFrdsTv.isVisible = false
                    dataBinding.emptyTextView.isVisible = true
                }
            }
        }

        dataBinding.backArrowIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        dataBinding.searchFriendsEdt.setOnEditorActionListener { p0, p1, p2 ->
            if (p0.text.isNullOrEmpty()) {
                adapter.submitList(friendsList)
                true
            } else {
                val filteredList =
                    friendsList.filter {
                        it.userName.uppercase()
                            .startsWith(dataBinding.searchFriendsEdt.text.toString().uppercase())
                    }
                adapter.setUpdatedData(ArrayList(filteredList))
                viewModel.totalFriendsList.value=filteredList.size
                true
            }
        }
        dataBinding.rvFriends.adapter = adapter
    }

    private fun onRemoveFriendClicked(item: FriendDetailResponse, position: Int, count: Int) {
        viewModel.removeFriend(item, userId, count)
    }

    override fun getData() {
        //
    }


}