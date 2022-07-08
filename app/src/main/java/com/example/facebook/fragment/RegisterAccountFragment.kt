package com.example.facebook.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentRegisterAccountBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.RegisterAccountViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*

class RegisterAccountFragment :
    BaseFragment<FragmentRegisterAccountBinding, RegisterAccountViewModel>() {
    override fun getViewModel() = RegisterAccountViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_register_account
    }

    override fun initViews() {
        dataBinding.createAccViewModel = viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                val action =
                    RegisterAccountFragmentDirections.actionCreateAccountFragmentToProfilePageFragment2()
                findNavController().navigate(action)
            }
        }
        dataBinding.dobEditText.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()
            datePicker.show(requireActivity().supportFragmentManager, "dob")
            datePicker.addOnPositiveButtonClickListener {
                // Respond to positive button click.
                val millis = datePicker.selection
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = millis ?: 0
                dataBinding.dobEditText.setText(getDateString(calendar))
            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                val action=RegisterAccountFragmentDirections.actionCreateAccountFragmentToProfilePageFragment2()
                findNavController().navigate(action)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.toastEvent.collectLatest {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        }
//        dataBinding.radiobtns.checkedRadioButtonId
    }
        private fun getDateString(calendar: Calendar): String {
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return format.format(calendar.time)
        }
}