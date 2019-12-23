package com.az3ez.newzbee

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.az3ez.daos.NewsResponse
import com.az3ez.newzbee.Adapter.Adapter
import com.azeeztech.apirequests.BaseRepository
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val repo = BaseRepository(this)
        repo.getTopHeadlines().observe(this, Observer {
            if (it.isSuccessful) {
                setupAdapter(it.data)
            } else {
                Toast.makeText(this@FeedActivity, it.error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupAdapter(data: NewsResponse?) {
        val adapter = Adapter(data!!.articles, this)
        adapter.setOnItemClickListener(object : Adapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                
            }

        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
