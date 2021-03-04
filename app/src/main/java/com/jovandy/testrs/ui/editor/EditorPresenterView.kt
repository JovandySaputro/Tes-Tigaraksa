package com.jovandy.testrs.ui.editor

import android.widget.EditText

interface EditorPresenterView {
    fun DoUpdate(id:String,
                 nama: String,
                 lat: String,
                 longt:String
    )
    fun DoCreate(nama: String,
                 lat: String,
                 longt:String
    )
    fun DoDelete(id: String
    )


    fun setProgressBarVisibility(visibility: Int)
}