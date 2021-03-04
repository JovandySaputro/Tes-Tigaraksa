package com.jovandy.testrs.ui.signin

import android.widget.CheckBox
import android.widget.EditText

interface SignInPresenterView {
    fun doSignIn(mobileORemail: EditText,
                 password: EditText,
    )
    fun setProgressBarVisibility(visibility: Int)
}