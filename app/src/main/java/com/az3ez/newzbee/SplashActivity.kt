package com.az3ez.newzbee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val timer = Timer()
        val timerTask = object : TimerTask() {
            override fun run() {
                startActivity(Intent(this@SplashActivity, FeedActivity::class.java))
            }
        }
        timer.schedule(timerTask, 1000)
    }
}
