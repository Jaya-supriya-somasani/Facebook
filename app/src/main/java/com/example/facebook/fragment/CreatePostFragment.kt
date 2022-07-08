package com.example.facebook.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreatePostBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.util.showProgressDialogue
import com.example.facebook.viewmodels.CreatePostViewModel
import kotlinx.coroutines.flow.collectLatest

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>() {

    override fun getViewModel() = CreatePostViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_create_post

    override fun initViews() {

        dataBinding.close.setOnClickListener {
            findNavController().popBackStack()
        }
        dataBinding.post.setOnClickListener {
            showProgressDialogue(requireContext(), "Uploading")
            viewModel.uploadPost(userId = "2", dataBinding.description.text.toString())
        }

        lifecycleScope.launchWhenResumed {
            viewModel.statusEvent.collectLatest {
                if (it) {
                    findNavController().popBackStack()
                }
            }
        }


        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }


}
