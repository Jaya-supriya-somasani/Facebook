package com.example.facebook.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentRegisterAccountBinding
import com.example.facebook.register.RegisterAccountViewModel
import com.example.facebook.util.BaseFragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*


class RegisterAccountActivity :
    BaseFragment<FragmentRegisterAccountBinding, RegisterAccountViewModel>() {
    override fun getViewModel() = RegisterAccountViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_register_account
    }

    override fun initViews() {
        registrationValidation()
        dataBinding.signBtn.setOnClickListener {
            registrationValidation()
        }
        dataBinding.createAccViewModel = viewModel
        lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                findNavController().popBackStack()
            }
        }
        dataBinding.loginBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        dataBinding.dobEditText.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()
            datePicker.show(this.parentFragmentManager, "dob")
            datePicker.addOnPositiveButtonClickListener {
                // Respond to positive button click.
                val millis = datePicker.selection
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = millis ?: 0
                dataBinding.dobEditText.setText(getDateString(calendar))
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDateString(calendar: Calendar): String {
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return format.format(calendar.time)
    }

    private fun registrationValidation() {
        if (dataBinding.userNameEdt.text.toString().isEmpty()) {
            dataBinding.usernameTv.error = "Please Enter User Name"
        } else if (dataBinding.emailEdt.text.toString().isEmpty()) {
            dataBinding.emailTv.error = "Please Enter user Email"
        } else if (dataBinding.newPswdEdt.text.toString().isEmpty()) {
            dataBinding.newPswdTv.error = "Please Enter Password"
        } else if (dataBinding.confirmPasswordEdt.text.toString().isEmpty()) {
            dataBinding.confirmPasswordTv.error = "Please Enter confirm password"
        } else if (dataBinding.dobEditText.text.toString().isEmpty()) {
            dataBinding.dobTextView.error = "Please Enter Date of Birth"
        } else {
            passwordValidation()
        }
    }

    private fun passwordValidation() {
        viewModel.passwordValidation()
    }

    override fun getData() {
        //
    }

}