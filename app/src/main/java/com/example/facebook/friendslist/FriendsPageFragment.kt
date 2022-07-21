package com.example.facebook.friendslist

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
            viewModel.removeFriendsEvent.collectLatest {
                adapter.notifyItemRemoved(it)
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
                true
            }
        }
        dataBinding.rvFriends.adapter = adapter
    }

    private fun onRemoveFriendClicked(item: FriendDetailResponse,position:Int) {
        viewModel.removeFriend(item,userId,position)
    }

//
//    private fun validateEmail() {
//        if (p0.isNullOrEmpty()) {
//            adapter.submitList(friendsList)
//        } else {
//            val filteredList =
//                friendsList.filter { it.userName.startsWith(dataBinding.searchFriendsEdt.text.toString()) }
//            adapter.setUpdatedData(ArrayList(filteredList))
//        }
//    }


}