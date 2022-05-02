package com.example.wildberriesweekone.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wildberriesweekone.databinding.ActivityServiceBinding
import android.content.Intent

class ServiceActivity : AppCompatActivity() {
    lateinit var binding: ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Service"

        val i = Intent(this, MusicPlayerService::class.java)

        with(binding){
            start.setOnClickListener {
                startService(i)
            }

            stop.setOnClickListener {
                stopService(i)
            }
        }
    }
}