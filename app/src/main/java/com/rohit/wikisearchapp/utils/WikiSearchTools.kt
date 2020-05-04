package com.rohit.wikisearchapp.utils

import android.content.Context
import android.widget.Toast
import com.rohit.wikisearchapp.AppController

/**
 * Created by rohit on 5/3/20.
 */

object WikiSearchTools {
    fun checkInternetStatus(context: Context?): Boolean {
        val status: String = NetworkUtil.getConnectivityStatusString(context)
        var isNetworkAvailable = false
        try {
            isNetworkAvailable = when (status) {
                "Connected to Internet with Mobile Data" -> true
                "Connected to Internet with WIFI" -> true
                else -> false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isNetworkAvailable
    }
    fun showToast(message : String){
        Toast.makeText(AppController.instance,message,Toast.LENGTH_SHORT).show()
    }
}