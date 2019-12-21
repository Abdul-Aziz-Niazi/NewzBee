package com.azeeztech.apirequests

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("everything")
    fun getEverything(): Call<ResponseBody>
}