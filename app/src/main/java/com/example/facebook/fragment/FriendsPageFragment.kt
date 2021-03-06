package com.example.facebook.fragment

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.adapter.AddFriendsListAdapter
import com.example.facebook.api.request.FriendDetailResponse
import com.example.facebook.databinding.FragmentUserFriendsBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.FriendsListViewModel
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

    private fun onRemoveFriendClicked(item: FriendDetailResponse) {
        viewModel.removeFriend(item)
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