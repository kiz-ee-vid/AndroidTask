package com.example.test_android.presentation.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_android.R
import com.example.test_android.databinding.ItemBannerBinding
import com.example.test_android.databinding.ItemCategoryBinding
import com.example.test_android.domain.ui_model.Product

class CategoryAdapter(val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private var categoryList = ArrayList<String>()
    private var currentCategory = 0

    inner class CategoryHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        with(holder.binding) {
            categoryTitle.text = categoryList[position]
            if (position == currentCategory){
                holder.binding.root.setCardBackgroundColor(Color.RED)
            }
            else holder.binding.root.setCardBackgroundColor(Color.WHITE)
        }
        holder.itemView.setOnClickListener {
            notifyItemChanged(currentCategory)
            currentCategory = holder.adapterPosition
            notifyItemChanged(currentCategory)
            itemClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(categories: ArrayList<String>, category: Int?) {
        categoryList.clear()
        categoryList.addAll(categories)
        if (category != null) {
            currentCategory = category
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}