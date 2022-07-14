package com.example.facebook.fragment
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.datastore.AppDataStore
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.ProfilePageViewModel
import kotlinx.coroutines.flow.collectLatest
class ProfilePageFragment :
    BaseFragment<com.example.facebook.databinding.FragmentProfilePageBinding, ProfilePageViewModel>() {
    override fun getViewModel() = ProfilePageViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_profile_page
    }

    override fun initViews() {
        val appDataStore = AppDataStore(requireContext())
        lifecycleScope.launchWhenCreated {
            appDataStore.userIdFlow.collectLatest {
                viewModel.getProfileData(it)
            }
        }
        dataBinding.changePasswordBtn.setOnClickListener {
            findNavController().navigate(deepLink = Uri.parse("app://my_facebook/change_password"))
        }
        lifecycleScope.launchWhenResumed {
            viewModel.profileData.collectLatest {
                dataBinding.data = it
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}