package com.example.homework18.service

import com.example.homework18.dataclass.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsersInfo(@Query("page") page: Int): ApiResponse
}