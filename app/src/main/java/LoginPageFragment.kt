//package com.example.facebook.fragment.login
//
//import android.widget.Toast
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import com.example.facebook.R
//import com.example.facebook.databinding.FragmentLoginPageBinding
//import com.example.facebook.datastore.AppDataStore
//import com.example.facebook.util.BaseFragment
//import kotlinx.coroutines.flow.collectLatest
//
//class LoginPageFragment : BaseFragment<FragmentLoginPageBinding, LoginPageViewModel>() {
//
//    override fun getViewModel() = LoginPageViewModel::class.java
//
//    override fun getResourceId(): Int {
//        return R.layout.fragment_login_page
//    }
//
//    override fun initViews() {
//
//        dataBinding.loginVM = viewModel
//        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            viewModel.loginEvent.collectLatest { pair ->
//                val action =
//                    LoginPageFragmentDirections.actionLoginPageFragmentToProfilePageFragment2()
//                findNavController().navigate(action)
//
//                AppDataStore.saveUserName(userName = pair.first, context = requireContext())
//                AppDataStore.saveUserId(userId = pair.second, context = requireContext())
//            }
//        }
//
//        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            viewModel.createAccountEvent.collectLatest {
//                val action=
//                    LoginPageFragmentDirections.actionLoginPageFragmentToCreateAccountFragment()
//                findNavController().navigate(action)
//            }
//        }
//        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            viewModel.forgotPasswordEvent.collectLatest {
//                val action=
//                    LoginPageFragmentDirections.actionLoginPageFragmentToChangePasswordFragment()
//                findNavController().navigate(action)
//            }
//        }
//
//        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            viewModel.toastEvent.collectLatest {
//                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
//}