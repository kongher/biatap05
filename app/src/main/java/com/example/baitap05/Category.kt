package com.example.baitap05.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("images") // Tên trường là 'images' trong API
    val images: String,
    @SerializedName("description")
    val description: String
)