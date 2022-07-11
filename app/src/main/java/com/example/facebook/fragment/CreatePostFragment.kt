package com.example.facebook.fragment

import android.app.ProgressDialog
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreatePostBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreatePostViewModel
import kotlinx.coroutines.flow.collectLatest
import java.util.*

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>() {

    override fun getViewModel() = CreatePostViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_create_post

    override fun initViews() {

        dataBinding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        dataBinding.postBtn.setOnClickListener {
            showProgressDialogue()
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

    fun showProgressDialogue() {
        val pd = ProgressDialog(requireContext()).apply {
            setMessage("Uploading")
        }
        pd.show()
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                pd.dismiss()
                timer.cancel()
            }
        }, 500)
    }
}
