package com.example.facebook.changepassword

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.DaggerApplicationComponent
import com.example.facebook.R
import com.example.facebook.databinding.FragmentChangePasswordBinding
import com.example.facebook.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel>() {

    override fun getViewModel(): Class<ChangePasswordViewModel> =
        ChangePasswordViewModel::class.java

    override fun getResourceId(): Int = R.layout.fragment_change_password

    override fun initViews() {
        setupListeners()
        val applicationComponent=DaggerApplicationComponent.builder().build()
        applicationComponent.inject(this)

        dataBinding.changePasswordBtn.setOnClickListener {
            if (validateEmail() && validatePassword() && validateConfirmPassword()) {
                viewModel.changePassword(
                    2,
                    dataBinding.passwordEt.text.toString(),
                    dataBinding.reEnterPasswordEt.text.toString()
                )
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.resetPasswordEvent.collectLatest {
                findNavController().popBackStack()
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(
                    requireContext(),
                    it,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

    }


    private fun setupListeners() {
        dataBinding.userEt.addTextChangedListener(TextFieldValidation(dataBinding.userEt))
        dataBinding.passwordEt.addTextChangedListener(TextFieldValidation(dataBinding.passwordEt))
        dataBinding.reEnterPasswordEt.addTextChangedListener(TextFieldValidation(dataBinding.reEnterPasswordEt))
    }


    private fun validateEmail(): Boolean {
        if (dataBinding.userEt.text.toString().trim().isEmpty()) {
            dataBinding.userTil.error = "Required Field!"
            dataBinding.userEt.requestFocus()
            return false
        } else {
            dataBinding.userTil.isErrorEnabled = false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        if (dataBinding.passwordEt.text.toString().trim().isEmpty()) {
            dataBinding.newPasswordTil.error = "Required Field!"
            dataBinding.passwordEt.requestFocus()
            return false
        } else if (dataBinding.passwordEt.text.toString().length < 5) {
            dataBinding.newPasswordTil.error = "password can't be less than 5"
            dataBinding.passwordEt.requestFocus()
            return false
        } else {
            dataBinding.newPasswordTil.isErrorEnabled = false
        }
        return true
    }

    private fun validateConfirmPassword(): Boolean {
        when {
            dataBinding.reEnterPasswordEt.text.toString().trim().isEmpty() -> {
                dataBinding.reEnterPasswordtil.error = "Required Field!"
                dataBinding.reEnterPasswordEt.requestFocus()
                return false
            }
            dataBinding.passwordEt.text.toString() != dataBinding.reEnterPasswordEt.text.toString() -> {
                dataBinding.reEnterPasswordtil.error = "Passwords don't match"
                dataBinding.reEnterPasswordEt.requestFocus()
                return false
            }
            else -> {
                dataBinding.reEnterPasswordtil.isErrorEnabled = false
            }
        }
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.userEt -> {
                    validateEmail()
                }
                R.id.passwordEt -> {
                    validatePassword()
                }
                R.id.reEnterPasswordEt -> {
                    validateConfirmPassword()
                }
            }
        }
    }

    override fun getData() {
        //
    }
}
