package com.azeeztech.apirequests

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.az3ez.apirequests.R
import com.az3ez.daos.ApiResponse
import com.az3ez.daos.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseRepository(var context: AppCompatActivity) {
    private var service: WebService
    var client: APIClient? = null

    init {
        client = APIClient()
        service = client!!.setup()
    }

    fun getTopHeadlines(): MutableLiveData<ApiResponse<NewsResponse>> {
        val data = MutableLiveData<ApiResponse<NewsResponse>>()
        service.getTopHeadlines("bbc-news",context.resources.getString(R.string.api_key))
            .enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    data.postValue(ApiResponse.success(response.body()!!))
                } else {
                    data.postValue(ApiResponse.failure(response.errorBody()?.string()))
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                data.postValue(ApiResponse.failure(t.message))
            }

        })
        return data
    }

    fun searchNews(query: String) {

    }

}