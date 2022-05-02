package com.example.wildberriesweekone.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.wildberriesweekone.databinding.ActivityBroadcastReceiverBinding
import android.R
import android.app.Activity
import android.view.View


class NetworkReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (isOnline(context)) {
            Toast.makeText(context,
                "Internet is connected", Toast.LENGTH_SHORT).show();
            //BroadcastReceiverActivity.dialog(true)
        }
        else{
            Toast.makeText(context,
                "Internet is not connected", Toast.LENGTH_SHORT).show();
           // BroadcastReceiverActivity.dialog(false)
        }
    }

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        //should check null because in airplane mode it will be null
        return netInfo != null && netInfo.isConnected
    }



}