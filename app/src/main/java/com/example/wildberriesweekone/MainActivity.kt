package com.example.wildberriesweekone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wildberriesweekone.broadcastreceiver.BroadcastReceiverActivity
import com.example.wildberriesweekone.contentprovider.ContentProviderActivity
import com.example.wildberriesweekone.databinding.ActivityMainBinding
import com.example.wildberriesweekone.service.ServiceActivity

class MainActivity : AppCompatActivity() {

    /*Комментарии к экрану:
    1.3. На MainActivity расположено 3 кнопки для перехода на другие activity.
    Из других Activity на главный экран можно вернуться нажатием стандратной кнопки "назад"
    */

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
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