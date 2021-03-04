package com.jovandy.testrs.ui.main

import com.jovandy.testrs.api.respone.listdata.DataItem

interface MainView {
    fun onSetProgressBarVisibility(visibility: Int)
    fun showSukses(dataItem: List<DataItem?>)
    fun showError(message: String)
}