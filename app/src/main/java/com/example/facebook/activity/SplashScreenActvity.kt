package com.example.facebook.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.facebook.R
import com.example.facebook.databinding.ActivitySplashScreenBinding


class SplashScreenActvity : AppCompatActivity() {
    //  private var START_ANIMATION = true

    private lateinit var binding: ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (savedInstanceState != null) {
//            START_ANIMATION = savedInstanceState.getBoolean("START_ANIMATION")
//        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        Handler().postDelayed({
            val i = Intent(this@SplashScreenActvity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, (1 * 1000).toLong())
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putBoolean("START_ANIMATION", false)
//    }


}
