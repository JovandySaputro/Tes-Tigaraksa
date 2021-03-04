package com.jovandy.testrs.ui.editor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.jovandy.testrs.ui.main.MainActivity
import com.jovandy.testrs.api.RetrofitClient
import com.jovandy.testrs.api.respone.createdata.PostCreate
import com.jovandy.testrs.api.respone.createdata.ResponseCreateData
import com.jovandy.testrs.api.respone.deletedata.ResponseDeleteData
import com.jovandy.testrs.api.respone.updatedata.PostUpdate
import com.jovandy.testrs.api.respone.updatedata.ResponseUpdateData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditorPresenter (var editorView: EditorView, val context: Context) : EditorPresenterView {

    override fun DoUpdate(id: String, nama: String, lat: String, longt: String) {
        editorView.onSetProgressBarVisibility(View.VISIBLE)
        var input = PostUpdate(nama, lat, longt)
        RetrofitClient.instance.updateItem(id,input)
            .enqueue(object : Callback<ResponseUpdateData> {
                override fun onFailure(call: Call<ResponseUpdateData>, t: Throwable) {
                    Toast.makeText(context, "Periksa kembali koneksi internet anda!",
                        Toast.LENGTH_LONG).show();
                    editorView.onSetProgressBarVisibility(View.GONE)
                }

                override fun onResponse(call: Call<ResponseUpdateData>, response: Response<ResponseUpdateData>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Sukses update",
                            Toast.LENGTH_LONG).show();
                        editorView.onSetProgressBarVisibility(View.GONE)
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                    } else {
                        Toast.makeText(context, "Gagal update",
                            Toast.LENGTH_LONG).show();
                        editorView.onSetProgressBarVisibility(View.GONE)
                    }
                }
            })
    }

    override fun DoCreate(nama: String, lat: String, longt: String) {
        editorView.onSetProgressBarVisibility(View.VISIBLE)
        var input = PostCreate(nama, lat, longt)
        RetrofitClient.instance.saveItem(input)
                .enqueue(object : Callback<ResponseCreateData> {
                    override fun onFailure(call: Call<ResponseCreateData>, t: Throwable) {
                        Toast.makeText(context, "Periksa kembali koneksi internet anda!",
                            Toast.LENGTH_LONG).show();
                        editorView.onSetProgressBarVisibility(View.GONE)
                    }

                override fun onResponse(call: Call<ResponseCreateData>, response: Response<ResponseCreateData>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Sukses update",
                            Toast.LENGTH_LONG).show();
                        editorView.onSetProgressBarVisibility(View.GONE)
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                    } else {
                        Toast.makeText(context, "Gagal update",
                            Toast.LENGTH_LONG).show();
                        editorView.onSetProgressBarVisibility(View.GONE)
                    }
                }
            })
    }

    override fun DoDelete(id: String) {
        editorView.onSetProgressBarVisibility(View.VISIBLE)
        RetrofitClient.instance.deleteItem(id)
            .enqueue(object : Callback<ResponseDeleteData> {
                override fun onFailure(call: Call<ResponseDeleteData>, t: Throwable) {
                    Toast.makeText(context, "Periksa kembali koneksi internet anda!",
                        Toast.LENGTH_LONG).show();
                    editorView.onSetProgressBarVisibility(View.GONE)
                }

                override fun onResponse(call: Call<ResponseDeleteData>, response: Response<ResponseDeleteData>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Sukses Delete",
                            Toast.LENGTH_LONG).show();
                        editorView.onSetProgressBarVisibility(View.GONE)
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                    } else {
                        Toast.makeText(context, "Gagal Delete",
                            Toast.LENGTH_LONG).show();
                        editorView.onSetProgressBarVisibility(View.GONE)
                    }
                }
            })
    }


    override fun setProgressBarVisibility(visibility: Int) {
        editorView.onSetProgressBarVisibility(visibility)
    }
}