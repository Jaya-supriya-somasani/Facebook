package com.example.facebook.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.facebook.R
import com.example.facebook.databinding.ActivityCreatePostBinding


class CreatePostActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_post)
        binding.close.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.post.setOnClickListener {
            val pd = ProgressDialog(this).apply {
                setMessage("Uploading")
                show()
            }
            uploadPost()
            pd.dismiss()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } 
    }

    private fun uploadPost() {
        //api calling
    }
}

