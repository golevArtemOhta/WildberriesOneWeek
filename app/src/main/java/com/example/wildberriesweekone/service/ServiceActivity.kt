package com.example.wildberriesweekone.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wildberriesweekone.databinding.ActivityServiceBinding
import android.content.Intent

class ServiceActivity : AppCompatActivity() {

    /*Комментарии к экрану:
    1.3. На данном экране происходит воспроизведение музыки (произведение Моцарта)
    На экарне расположены две кнопки для старта и остановки музыкального воспроизведения.

    1.4. Service используется в приложениях где нужно частое обновление без взаимодейтсвия
    с пользователем. Самый распространнеый пример исползования - это MP3 плееры.
    Популярные приложения использующие Service:
    - MP3 плееры (spotify, яндекс музыка)
    - Букмекерские конторы (1xbet, fonbet);
    - Приложения для отслеживания результатов(LiveSport);
    - Приложения с курсами валют (курсы валют Forex)
    - Банковские приложения (Сбербанк, Тинькофф, Альфа-банк, Тинькофф инвестиции)
     */

    lateinit var binding: ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Service"

        val i = Intent(this, MusicPlayerService::class.java)

        with(binding) {
            start.setOnClickListener {
                startService(i)
            }

            stop.setOnClickListener {
                stopService(i)
            }
        }
    }
}