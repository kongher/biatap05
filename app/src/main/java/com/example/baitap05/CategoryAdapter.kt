package com.example.baitap05.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baitap05.R
import com.example.baitap05.model.Category

class CategoryAdapter(
    private val context: Context,
    private var array: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    // Lớp ViewHolder
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val images: ImageView = itemView.findViewById(R.id.image_cate)
        val tenSp: TextView = itemView.findViewById(R.id.tvNameCategory)

        // Xử lý sự kiện click
        init {
            itemView.setOnClickListener {
                val categoryName = tenSp.text.toString()
                Toast.makeText(itemView.context, "Bạn đã chọn category: $categoryName", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false) // layout.item_category theo ảnh
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = array[position]

        // Gán tên sản phẩm
        holder.tenSp.text = category.name

        // Load ảnh với Glide (sử dụng trường 'images' từ Model)
        Glide.with(context)
            .load(category.images)
            .into(holder.images)
    }

    override fun getItemCount(): Int {
        // Tránh lỗi null pointer, luôn trả về số lượng chính xác
        return array.size
    }

    // Hàm cập nhật dữ liệu (cần thiết khi dữ liệu API về)
    fun updateData(newArray: List<Category>) {
        this.array = newArray
        notifyDataSetChanged()
    }
}