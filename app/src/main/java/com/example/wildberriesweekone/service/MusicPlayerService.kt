package com.example.wildberriesweekone.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.lang.UnsupportedOperationException
import android.media.MediaPlayer
import com.example.wildberriesweekone.R


class MusicPlayerService: Service() {
    var ambientMediaPlayer: MediaPlayer? = null
    override fun onBind(p0: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        ambientMediaPlayer = MediaPlayer.create(this, R.raw.mocart)
        ambientMediaPlayer!!.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ambientMediaPlayer!!.start()
        return START_STICKY
    }

    override fun onDestroy() {
        ambientMediaPlayer!!.stop()
    }

}