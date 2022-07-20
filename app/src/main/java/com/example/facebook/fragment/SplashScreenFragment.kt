package com.example.facebook.fragment

//
//class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>() {
//    override fun getViewModel(): Class<SplashScreenViewModel> = SplashScreenViewModel::class.java
//
//    override fun getResourceId(): Int = R.layout.fragment_splash_screen
//    override fun initViews() {
//        val appDataStore = AppDataStore(requireContext())
//        lifecycleScope.launchWhenResumed {
//            appDataStore.userLoggedStatusFlow.collectLatest {
//                Log.d("Splash", "initViews: $it")
//                viewModel.moveToNextScreen(it)
//            }
//        }
//        lifecycleScope.launchWhenResumed {
//            viewModel.navigateToNextScreenEvent.collectLatest {
//                moveScreen(it)
//            }
//        }
//
//    }
//
//    private fun moveScreen(status: Boolean) {
//        if (status) {
//            activity?.let { move ->
//                val intent = Intent(move, MainActivity::class.java)
//                move.startActivity(intent)
//            }
//        } else {
//            Log.d("Splash", "Login Screen")
////            val action =
////                SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
////            findNavController()
////                .navigate(action)
//        }
//    }
//}
