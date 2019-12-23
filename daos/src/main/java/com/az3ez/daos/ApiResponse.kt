package com.az3ez.daos

class ApiResponse<T> constructor(isSuccessful: Boolean, data: T?, err: String?) {
    var data: T? = null
    var error: String? = null
    var isSuccessful = false
    init {
        this.data = data
        this.error = err
        this.isSuccessful = isSuccessful
    }


    companion object {
        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(true, data, null)
        }

        fun <T> failure(err: String?): ApiResponse<T> {
            return ApiResponse(false, null, err)
        }
    }

}