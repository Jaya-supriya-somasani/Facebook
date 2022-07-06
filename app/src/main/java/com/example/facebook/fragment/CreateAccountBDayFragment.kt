package com.example.facebook.fragment

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facebook.R
import com.example.facebook.databinding.FragmentCreateAccountBdayPageBinding
import com.example.facebook.util.BaseFragment
import com.example.facebook.viewmodels.CreateAccBdayViewModel
import kotlinx.coroutines.flow.collectLatest

class CreateAccountBDayFragment:
    BaseFragment<FragmentCreateAccountBdayPageBinding, CreateAccBdayViewModel>(){
    override fun getViewModel() = CreateAccBdayViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.fragment_create_account_bday_page
    }

    override fun initViews() {
        dataBinding.userBdayVM = viewModel

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.createUserBdayEvent.collectLatest {
                val action =CreateAccountBDayFragmentDirections.navCreateAccBDayFragmentToCreateAccGenderFragment()
                findNavController().navigate(action)
            }
        }

//        val datePicker =
//            MaterialDatePicker.Builder.datePicker()
//                .setTitleText("Select date")
//                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
//                .build()
//
//        datePicker.show()
//    }
//    @RequiresApi(Build.VERSION_CODES.N)
//    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
//        // Use the current date as the default date in the picker
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//        return DatePickerDialog(requireContext(), this, year, month, day)}
//
//
//        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
//            year
//        }
//    fun showDatePickerDialog(v: View) {
//        val newFragment = DatePickerFragment()
//        newFragment.show(supportFragmentManager, "datePicker")
//    }
    }
}