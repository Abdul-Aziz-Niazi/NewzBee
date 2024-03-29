package com.azeeztech.apirequests

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    companion object {
        const val TIMEOUT = 60
    }

    fun setup(): WebService {
        val okHttpBuilder = OkHttpClient.Builder()

//        okHttpBuilder.addInterceptor(HttpLoggingInterceptor())

        val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/").client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(Gson())).build()
        return retrofit.create(WebService::class.java)
    }

}