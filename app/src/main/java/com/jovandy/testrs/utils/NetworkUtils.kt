package com.jovandy.testrs.utils

import android.annotation.SuppressLint
import android.content.Context

class NetworkUtils {

    companion object {

        /**
         * Extension method to get connectivityManager for Context.
         */

        @SuppressLint("MissingPermission")
        fun isNetworkConnected(con : Context): Boolean {
            return con.connectivityManager?.activeNetworkInfo != null
        }
    }

}