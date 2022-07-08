package com.example.facebook.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<Binding : ViewDataBinding, T : BaseViewModel> : AppCompatActivity(),
    LifecycleOwner {

    abstract fun setupViews()
    abstract fun getViewModel(): Class<T>
    lateinit var viewModel: T
    protected lateinit var dataBinding: Binding
    abstract fun getResourceId(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding=DataBindingUtil.setContentView(this,getResourceId())
        viewModel = ViewModelProvider(this)[getViewModel()]
        setupViews()
    }

}