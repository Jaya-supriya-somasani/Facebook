package com.example.facebook.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorMessage")
fun TextInputLayout.showErrorMessage(errorMessage: String?) {
    this.error = errorMessage
}