package com.jovandy.testrs.ui.main

import android.content.Context
import android.view.View
import android.widget.Toast
import com.jovandy.testrs.api.RetrofitClient
import com.jovandy.testrs.api.respone.listdata.ResponseListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter (var mainView: MainView, val context: Context) : MainPresenterView {
    override fun doGetList() {
        mainView.onSetProgressBarVisibility(View.VISIBLE)
        RetrofitClient.instance.getListData()
            .enqueue(object : Callback<ResponseListData> {
                override fun onFailure(call: Call<ResponseListData>, t: Throwable) {
                    Toast.makeText(context, "Periksa kembali koneksi internet anda!",
                        Toast.LENGTH_LONG).show();
                    mainView.onSetProgressBarVisibility(View.GONE)
                }

                override fun onResponse(call: Call<ResponseListData>, response: Response<ResponseListData>) {
                    if (response.isSuccessful) {

                        mainView.showSukses(response?.body()?.data!!)
                        mainView.onSetProgressBarVisibility(View.GONE)
                    } else {
                        Toast.makeText(context, "Server belum aktif",
                            Toast.LENGTH_LONG).show();
                        mainView.onSetProgressBarVisibility(View.GONE)
                    }
                }
            })
    }

    override fun setProgressBarVisibility(visibility: Int) {
        mainView.onSetProgressBarVisibility(visibility)
    }
}