package com.example.facebook.activity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.facebook.R
import com.example.facebook.databinding.ActivityHomeBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.HomeActivityViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { false }
    }
    override fun getViewModel(): Class<HomeActivityViewModel> = HomeActivityViewModel::class.java

    override fun getResourceId(): Int = R.layout.activity_home
    override fun setupViews() {

    }


//    override fun setupViews() {
//        val appdataStore= AppDataStore(this)
//        lifecycleScope.launchWhenResumed {
//            appdataStore.userLoggedStatusFlow.collectLatest {
//                viewModel.moveToNextScreen(it)
//            }
//        }

//        lifecycleScope.launchWhenResumed {
//            viewModel.navigateNextScreenEvent.collectLatest {
//
//                moveToScreen(it)
//
//            }
//        }
//    }
//    private fun moveToScreen(status:Boolean){
//        if (status){
//                val intent = Intent(this, MainActivity::class.java)
//                this.startActivity(intent)
//            }
//        else{
//            launchingSplashScreen()
//        }
//    }

//    private fun launchingSplashScreen(){
//        splashScreen.setKeepOnScreenCondition { false }
//
//    }

}