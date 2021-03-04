package com.jovandy.testrs.ui.signin


import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jovandy.testrs.R
import com.jovandy.testrs.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_signin.*
import java.util.jar.Manifest


class SignInActivity: AppCompatActivity(),SignInView {

    private lateinit var signInPresenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        supportActionBar!!.hide()
        signInPresenter = SignInPresenter(this, this)
        setupView()
        permission()
    }
    private fun permission() {
        if (ContextCompat.checkSelfPermission(this@SignInActivity,
               ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@SignInActivity,
                    ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this@SignInActivity,
                    arrayOf(ACCESS_FINE_LOCATION), 1)
            } else {
                ActivityCompat.requestPermissions(this@SignInActivity,
                    arrayOf(ACCESS_FINE_LOCATION), 1)
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@SignInActivity,
                         ACCESS_FINE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
    private fun setupView() {
        signInPresenter.setProgressBarVisibility(View.GONE)
        loginBtn.setOnClickListener {
            signInPresenter.setProgressBarVisibility(View.VISIBLE)
            signInPresenter.doSignIn(login_mobile, login_password)
        }
        createAccount.setOnClickListener(){
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        forgot_password.setOnClickListener(){
            Toast.makeText(this, "Lupa password masih belum tersedia.", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSetProgressBarVisibility(visibility: Int) {
        progress_bar.visibility = visibility
    }

    override fun showError(message: String) {

    }
}