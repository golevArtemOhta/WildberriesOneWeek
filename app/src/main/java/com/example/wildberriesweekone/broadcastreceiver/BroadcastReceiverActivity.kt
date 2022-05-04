package com.example.wildberriesweekone.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wildberriesweekone.databinding.ActivityBroadcastReceiverBinding


class BroadcastReceiverActivity : AppCompatActivity() {

    /*Комментарии к экрану:
    1.3. На данном экране показывается подключение интернета. При отключенном интернете экран
    окрашивается в красный цвет. В центре экрана появляется надпись "Internet is not connected".
    Также появляется надпись Toast с надписью "Internet is not connected"
    При подключенном интернете экран окрашивается в зеленый цвет.
    В центре экрана появляется надпись "Internet is connected".
    Также появляется надпись Toast с надписью "Internet is connected"

    1.4. Broadcast Receiver используется в приложениях, где нужно получать какие-то уведомления от
    системы.
    Популярные приложения использующие Broadcast Receiver:
    - календари;
    - будильники
     */

    var br: BroadcastReceiver? = null
    lateinit var binding: ActivityBroadcastReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "BroadcastReceiver"

        br = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val connMgr = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                val mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                if (isOnline(context)) {
                    Toast.makeText(context,
                        getString(com.example.wildberriesweekone.R.string.Internet_is_connected), Toast.LENGTH_SHORT).show();
                    binding.tvInternet.text = getString(com.example.wildberriesweekone.R.string.Internet_is_connected)

                    getWindow().getDecorView().setBackgroundColor(Color.GREEN)
                    binding.tvInternet.setTextColor(Color.WHITE)

                }
                else{
                    Toast.makeText(context,
                        getString(com.example.wildberriesweekone.R.string.Internet_is_not_connected), Toast.LENGTH_SHORT).show();
                    binding.tvInternet.text = getString(com.example.wildberriesweekone.R.string.Internet_is_not_connected)
                    getWindow().getDecorView().setBackgroundColor(Color.RED)
                    binding.tvInternet.setTextColor(Color.WHITE)
                }
            }

            fun isOnline(context: Context): Boolean {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = cm.activeNetworkInfo
                //should check null because in airplane mode it will be null
                return netInfo != null && netInfo.isConnected
            }
        }

        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(br, filter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }


}