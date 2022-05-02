package com.example.wildberriesweekone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wildberriesweekone.broadcastreceiver.BroadcastReceiverActivity
import com.example.wildberriesweekone.contentprovider.ContentProviderActivity
import com.example.wildberriesweekone.databinding.ActivityMainBinding
import com.example.wildberriesweekone.service.ServiceActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            serviceBtn.setOnClickListener {
                val myIntent = Intent(this@MainActivity, ServiceActivity::class.java)
                startActivity(myIntent)
            }

            broadcastReciverBtn.setOnClickListener {
                val myIntent = Intent(this@MainActivity, BroadcastReceiverActivity::class.java)
                startActivity(myIntent)
            }

            contentProviderBtn.setOnClickListener {
                val myIntent = Intent(this@MainActivity, ContentProviderActivity::class.java)
                startActivity(myIntent)
            }

        }

    }
}