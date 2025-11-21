package com.example.baitap05.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Định nghĩa Retrofit Client (Singleton)
object RetrofitClient {
    // ĐƯỜNG DẪN API CƠ SỞ THEO YÊU CẦU
    private const val BASE_URL = "http://app.iotstar.vn/appfoods/"

    // Lazy initialization (khởi tạo khi được dùng lần đầu)
    // SỬ DỤNG 'val' (Kotlin) thay vì 'static Retrofit' (Java)
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}