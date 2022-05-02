package com.example.wildberriesweekone.broadcastreceiver

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.IntentFilter
import android.widget.TextView
import com.example.wildberriesweekone.R
import com.example.wildberriesweekone.databinding.ActivityBroadcastReceiverBinding



class BroadcastReceiverActivity : AppCompatActivity() {
    lateinit var binding: ActivityBroadcastReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "BroadcastReceiver"

        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(NetworkReceiver(), filter)

    }

//    companion object {
//        fun dialog(isOnline: Boolean) {
//            if (isOnline){
//                binding.tv
//            }
//        }
//    }
}