package com.example.facebook.activity

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.facebook.R
import com.example.facebook.databinding.ActivityRegisterAccountBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.RegisterAccountViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*

class RegisterAccounActivity :
    BaseActivity<ActivityRegisterAccountBinding, RegisterAccountViewModel>() {
    override fun getViewModel() = RegisterAccountViewModel::class.java

    override fun getResourceId(): Int {
        return R.layout.activity_register_account
    }

    override fun setupViews() {
        registrationValidation()

        dataBinding.createAccViewModel = viewModel
        lifecycleScope.launchWhenResumed {
            viewModel.createAccountEvent.collectLatest {
                startActivity(Intent(this@RegisterAccounActivity,MainActivity::class.java))
            }
        }
        dataBinding.dobEditText.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()
            datePicker.show(this.supportFragmentManager, "dob")
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
                Toast.makeText(this@RegisterAccounActivity,it,Toast.LENGTH_SHORT).show()
            }
        }
//        dataBinding.radiobtns.checkedRadioButtonId
    }
        private fun getDateString(calendar: Calendar): String {
            val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            return format.format(calendar.time)
        }
    private fun registrationValidation(){
        if (dataBinding.userNameEdt.text?.isEmpty() == true){
            dataBinding.usernameTv.error="Please Enter User Name"
        }
        else if (dataBinding.emailEdt.text?.isEmpty() == true){
            dataBinding.emailTv.error="Please Enter user Email"
        }
        else if (dataBinding.newPswdEdt.text?.isEmpty()==true){
            dataBinding.newPswdTv.error="Please Enter Password"
        }
        else if (dataBinding.confirmPasswordEdt.text?.isEmpty()==true){
            dataBinding.confirmPasswordTv.error="Please Enter confirm password"
        }
        else if (dataBinding.dobEditText.text?.isEmpty()==true){
            dataBinding.dobTextView.error="Please Enter Date of Birth"
        }
//        else if (dataBinding.radiobtns.isSelected==false){
//            dataBinding.radiobtns.error="Please Enter Gender"
//        }
        else{
            passwordValidation()
        }
    }
    private fun passwordValidation(){

    }

}