package com.example.facebook.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.facebook.datastore.AppDataStore
import kotlinx.coroutines.flow.collectLatest

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }

        lifecycleScope.launchWhenResumed {
            AppDataStore(applicationContext).userLoggedStatusFlow.collectLatest { isLoggedIn ->
                if (isLoggedIn) {
                    //Dashboard screen
                    Log.d("Main Activity ","Login Status $isLoggedIn")
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else {
                    //Login screen

                    Log.d("HomeActivity","Login Status $isLoggedIn")
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                }
            }
        }
    }

}