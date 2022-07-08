package com.example.facebook.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.facebook.R
import com.example.facebook.databinding.ActivitySplashScreenBinding


class SplashScreenActvity : AppCompatActivity() {
    //  private var START_ANIMATION = true
    private lateinit var binding: ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        Handler().postDelayed({
            val sharedPref: SharedPreferences =
                this.getSharedPreferences(SHAREDPREFFILE, 0)
            val status = sharedPref.getBoolean("isLogged", true)
            if (status) {
                val i = Intent(this@SplashScreenActvity, MainActivity::class.java)
                startActivity(i)
            } else {
                val i = Intent(this@SplashScreenActvity, HomeActivity::class.java)
                startActivity(i)
            }
            finish()
        }, (1 * 1000).toLong())
    }

    companion object {
        const val SHAREDPREFFILE = "loggedSharedPreferences"
    }
}
