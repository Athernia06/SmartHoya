package com.example.myapplication.core.util.handler

import android.content.Context
import android.net.ConnectivityManager

interface IConnectionHandler {
    fun isInternetConnected(): Boolean
}

class ConnectionHandler(private val context: Context) : IConnectionHandler {
    override fun isInternetConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null
    }
}