package com.example.test_android.presentation.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_android.R
import com.example.test_android.databinding.ItemProductBinding
import com.example.test_android.domain.ui_model.Product
import com.example.test_android.presentation.di.App

class MenuAdapter :
    RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    private var productList = ArrayList<Product>()

    inner class MenuHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        with(holder.binding) {
            Glide.with(productImage)
                .load(productList[position].image)
                .into(productImage)
            productTitle.text = productList[position].title
            productDescription.text = productList[position].description
            productButton.text = App.getContext()?.getString(R.string.title_button, productList[position].price) ?: "Error"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(rockets: List<Product>) {
        productList.clear()
        productList.addAll(rockets)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}