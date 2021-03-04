package com.jovandy.testrs.ui.editor

interface EditorView {
    fun onSetProgressBarVisibility(visibility: Int)

    fun showError(message: String)
}