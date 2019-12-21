package com.az3ez.newzbee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azeeztech.apirequests.BaseRepository

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val repo = BaseRepository()
    }
}
