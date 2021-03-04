package com.jovandy.testrs.ui.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.jovandy.testrs.ui.main.MainActivity
import com.jovandy.testrs.database.sql.DatabaseHelper

class SignInPresenter (var signInView: SignInView, val context: Context) : SignInPresenterView {
    private lateinit var databaseHelper: DatabaseHelper

    override fun doSignIn(mobileORemail: EditText, password: EditText) {
        databaseHelper = DatabaseHelper(context)
        if (mobileORemail?.text.isNullOrEmpty()) {
            mobileORemail?.error=("Email tidak boleh kosong")
            mobileORemail.requestFocus()
            signInView.onSetProgressBarVisibility(View.GONE)
        } else if (password?.text.isNullOrEmpty()) {
            Toast.makeText(context, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            password.requestFocus()
            signInView.onSetProgressBarVisibility(View.GONE)
        }else{
            if (databaseHelper!!.checkUser(mobileORemail?.text.toString().trim(), password?.text.toString().trim())) {


                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
                signInView.onSetProgressBarVisibility(View.GONE)
                Toast.makeText(context, "Sukses",
                    Toast.LENGTH_LONG).show();
            } else {
                signInView.onSetProgressBarVisibility(View.GONE)
                Toast.makeText(context, "Email atau Password salah",
                    Toast.LENGTH_LONG).show();
            }

        }
    }

    override fun setProgressBarVisibility(visibility: Int) {
        signInView.onSetProgressBarVisibility(visibility)
    }
}