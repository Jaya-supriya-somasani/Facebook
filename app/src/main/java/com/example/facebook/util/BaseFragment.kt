package com.example.facebook.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.facebook.internetconnection.NetworkStatus
import com.example.facebook.internetconnection.NetworkStatusHelper


abstract class BaseFragment<MyBinding : ViewDataBinding, VM : ViewModel> : Fragment() {
    abstract fun getViewModel(): Class<VM>
    lateinit var viewModel: VM
    abstract fun getResourceId(): Int
    protected lateinit var dataBinding: MyBinding
    abstract fun initViews()
    abstract fun getData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getResourceId(), container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = viewLifecycleOwner
//        viewModel = ViewModelProvider(this)[getViewModel()]
        initViews()

    }

    override fun onResume() {
        super.onResume()
        NetworkStatusHelper(requireContext()).observe(viewLifecycleOwner){
            when(it){
                NetworkStatus.Available->{getData()}

                NetworkStatus.Unavailable->{
                    Toast.makeText(requireContext(),"No Internet Connection", Toast.LENGTH_SHORT).show()}

//                NetworkStatus.UnDetermined->{
//                    Toast.makeText(requireContext(),"Undetermined Internet Connection",Toast.LENGTH_SHORT).show()}
            }
        }
    }
}