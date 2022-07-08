package com.example.facebook.util

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import androidx.annotation.IdRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.example.facebook.api.response.BaseResponse
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Response
import java.util.*

@BindingAdapter("errorMessage")
fun TextInputLayout.showErrorMessage(errorMessage: String?) {
    this.error = errorMessage
}

fun Activity.findNavController(@IdRes viewId: Int): NavController =
    Navigation.findNavController(this, viewId)

fun NavDirections.navigate(fragment: Fragment) {
    fragment.findNavController().navigate(this)
}

fun NavDestination.matches(
    @IdRes destId: Int
): Boolean {
    var currentDestination: NavDestination? = this
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}

typealias ApiResponse<R> = Response<BaseResponse<R>>


fun showProgressDialogue(context: Context, message: String) {
    val pd = ProgressDialog(context).apply {
        setMessage(message)
    }
    pd.show()
    val timer = Timer()
    timer.schedule(object : TimerTask() {
        override fun run() {
            pd.dismiss()
            timer.cancel()
        }
    }, 750)
}


