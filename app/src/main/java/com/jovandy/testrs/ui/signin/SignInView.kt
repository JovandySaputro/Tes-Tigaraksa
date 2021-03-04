package com.jovandy.testrs.ui.signin

interface SignInView {
    fun onSetProgressBarVisibility(visibility: Int)

    fun showError(message: String)
}