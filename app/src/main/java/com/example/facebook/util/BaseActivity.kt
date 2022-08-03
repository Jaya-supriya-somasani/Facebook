package com.example.facebook.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import javax.inject.Inject

abstract class BaseActivity<ABinding : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    LifecycleOwner {

    abstract fun setupViews()
    abstract fun getViewModel(): Class<VM>
    @Inject
    lateinit var viewModel: VM
    protected lateinit var dataBinding: ABinding
    abstract fun getResourceId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding=DataBindingUtil.setContentView(this,getResourceId())
        setupViews()
    }

//    private fun getViewModelClass(): Class<VM> {
//        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
//        return type as Class<VM>
//    }

    abstract fun getViewBinding(): ABinding


}