package com.example.baitap05.api

import com.example.baitap05.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // Endpoint THEO YÊU CẦU: categories.php
    // URL hoàn chỉnh: http://app.iotstar.vn/appfoods/categories.php
    @GET("categories.php")
    fun getCategoryAll(): Call<List<Category>>
}