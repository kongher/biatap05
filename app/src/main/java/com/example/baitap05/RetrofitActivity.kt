package com.example.baitap05
import com.example.baitap05.R

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baitap05.adapter.CategoryAdapter
import com.example.baitap05.api.ApiService
import com.example.baitap05.api.RetrofitClient
import com.example.baitap05.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() { // Đổi thành Activity bạn đang dùng

    private lateinit var rcCate: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var apiService: ApiService
    private var categoryList: List<Category> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // R.layout.activity_retrofit theo ảnh bạn cung cấp
        setContentView(R.layout.activity_retrofit)

        AnhXa()

        // Khởi tạo Adapter với danh sách rỗng ban đầu
        categoryAdapter = CategoryAdapter(this, categoryList)

        // Thiết lập RecyclerView
        rcCate.layoutManager = GridLayoutManager(this, 2) // Dùng GridLayoutManager
        rcCate.adapter = categoryAdapter

        // Khởi tạo API Service
        apiService = RetrofitClient.instance.create(ApiService::class.java)

        GetCategory() // Load dữ liệu cho category
    }

    private fun AnhXa() {
        // R.id.rc_category theo ảnh bạn cung cấp
        rcCate = findViewById(R.id.rc_category)
    }

    private fun GetCategory() {
        apiService.getCategoryAll().enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if (response.isSuccessful) {
                    // Dữ liệu trả về thành công
                    val list = response.body() ?: emptyList()
                    categoryList = list
                    categoryAdapter.updateData(categoryList) // Cập nhật Adapter
                    Log.d("API_CALL", "Dữ liệu trả về thành công: ${list.size} items")
                } else {
                    // Lỗi phản hồi (ví dụ: 404, 500)
                    Log.e("API_CALL", "Lỗi phản hồi: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                // Lỗi kết nối (ví dụ: không có internet, Base URL sai)
                Log.e("API_CALL", "Lỗi kết nối API: ${t.message}")
            }
        })
    }
}