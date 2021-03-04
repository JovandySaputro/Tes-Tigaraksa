package com.jovandy.testrs.ui.main

import android.widget.EditText

interface MainPresenterView {
    fun doGetList(
    )
    fun setProgressBarVisibility(visibility: Int)
}