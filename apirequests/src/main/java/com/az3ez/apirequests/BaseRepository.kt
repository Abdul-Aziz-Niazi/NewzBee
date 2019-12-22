package com.azeeztech.apirequests

class BaseRepository {
    private var service: WebService
    var client: APIClient? = null

    init {
        client = APIClient()
        service = client!!.setup()
    }

    fun getTopHeadlines(){

    }

    fun searchNews(query:String){

    }

}