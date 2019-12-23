package com.azeeztech.apirequests

import com.az3ez.daos.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("sources")sources:String,@Query("apiKey") apiKey:String): Call<NewsResponse>
}