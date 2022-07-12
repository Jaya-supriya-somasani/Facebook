package com.example.facebook.fragment

import android.app.ProgressDialog
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreatePostBinding
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreatePostViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>() {

    override fun getViewModel() = CreatePostViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_create_post

    override fun initViews() {
        val appDataStore = AppDataStore(requireContext())
        dataBinding.postBtn.setOnClickListener {
            lifecycleScope.launch {
                appDataStore.userIdFlow.collectLatest {
                    showProgressDialogue()
                    viewModel.uploadPost(it, dataBinding.description.text.toString())
                }
            }
        }
        dataBinding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
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
