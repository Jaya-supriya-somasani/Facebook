package com.example.facebook.fragment

import android.annotation.SuppressLint
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.adapter.AddFriendsListAdapter
import com.example.facebook.api.request.AllFriendsListResponse
import com.example.facebook.databinding.FragmentUserFriendsBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.FriendsListViewModel
import kotlinx.coroutines.flow.collectLatest


class FriendsPageFragment : BaseFragment<FragmentUserFriendsBinding, FriendsListViewModel>() {
    override fun getViewModel() = FriendsListViewModel::class.java
    val adapter = AddFriendsListAdapter(::onRemoveClicked)
    override fun getResourceId(): Int {
        return R.layout.fragment_user_friends
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initViews() {
        dataBinding.userFriendsVM = viewModel
        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenResumed {
            viewModel.friendsList.collectLatest {
                adapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            appDataStore.userIdFlow.collectLatest {
                viewModel.getFriendsList(it)
            }
        }
        dataBinding.rvFriends.adapter = adapter
        dataBinding.backArrowIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        dataBinding.searchFriendsEdt.setOnEditorActionListener { v, actionId, event ->
            Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun onRemoveClicked(item: AllFriendsListResponse) {

    }
}