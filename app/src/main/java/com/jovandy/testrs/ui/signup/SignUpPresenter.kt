package com.jovandy.testrs.ui.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.jovandy.testrs.ui.main.MainActivity
import com.jovandy.testrs.R
import com.jovandy.testrs.database.model.User
import com.jovandy.testrs.database.sql.DatabaseHelper
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUpPresenter(var signUpView: SignUpView, val context: Context) : SignUpPresenterView {

    private lateinit var databaseHelper: DatabaseHelper
    override fun doSignUp(
        fullName: EditText,
        emailId: EditText,
        mobileNumber: EditText,
        password: EditText,
        repeat_pass: EditText,
        terms_conditions: CheckBox
    ) {
        databaseHelper = DatabaseHelper(context)
        if (fullName?.text.isNullOrEmpty()) {
            fullName?.error=("Nama tidak boleh kosong")
            fullName.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (emailId?.text.isNullOrEmpty()) {
            emailId.setError("Email tidak boleh kosong")
            emailId.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (!isEmailValid(emailId?.text.toString())) {
            emailId.setError("Periksa kembali email anda")
            emailId.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (mobileNumber?.text.isNullOrEmpty()) {
            mobileNumber.setError("Nomor tidak boleh kosong")
            mobileNumber.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (!isValidMobile(mobileNumber?.text.toString())) {
            mobileNumber.setError("Nomor telepon tidak valid")
            mobileNumber.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (password?.text.isNullOrEmpty()) {
           Toast.makeText(context, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            password.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (password?.text.length < 6) {
            Toast.makeText(
                context,
                context.getString(R.string.password_min_length),
                Toast.LENGTH_SHORT
            ).show()
            password.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (repeat_pass?.text.isNullOrEmpty()) {
            Toast.makeText(context, "Ulangi Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            repeat_pass.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (password?.text.toString() != repeat_pass?.text.toString()) {
            Toast.makeText(
                context,
                context.getString(R.string.password_differents),
                Toast.LENGTH_SHORT
            ).show()
            repeat_pass.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (!isValidPassword(password?.text.toString())) {
            Toast.makeText(context, context.getString(R.string.password_rules), Toast.LENGTH_LONG).show()
            password.requestFocus()
            signUpView.onSetProgressBarVisibility(View.GONE)
        } else if (!terms_conditions.isChecked()) {
            Toast.makeText(context, "Terima Syarat & Ketentuan", Toast.LENGTH_SHORT).show()
            signUpView.onSetProgressBarVisibility(View.GONE)
        }else{

            var user = User(name = fullName?.text.toString(), nomor = mobileNumber?.text.toString(),
                email = emailId?.text.toString(),
                password = password?.text.toString())

            databaseHelper!!.addUser(user)
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            (context as Activity).finish()
        }
    }

    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_!.])(?=\\S+$).{7,16}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
    fun isValidMobile(phone: String): Boolean {
        return if (!Pattern.matches("[a-zA-Z]+", phone)) {
            phone.length > 6 && phone.length <= 13
        } else false
    }
    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
    override fun setProgressBarVisibility(visibility: Int) {
        signUpView.onSetProgressBarVisibility(visibility)
    }
}