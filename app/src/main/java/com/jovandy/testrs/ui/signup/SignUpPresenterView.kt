package com.jovandy.testrs.ui.signup

import android.widget.CheckBox
import android.widget.EditText

interface SignUpPresenterView {

    fun doSignUp(fullName: EditText,
                emailId: EditText,
                mobileNumber: EditText,
                password: EditText,
                repeat_pass: EditText,
                terms_conditions : CheckBox)
    fun setProgressBarVisibility(visibility: Int)
}