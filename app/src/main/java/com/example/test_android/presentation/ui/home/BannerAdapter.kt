package com.example.test_android.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_android.R
import com.example.test_android.databinding.ItemBannerBinding

class BannerAdapter() :
    RecyclerView.Adapter<BannerAdapter.BannerHolder>() {

    private var bannerList = listOf(R.drawable.ic_banner0, R.drawable.ic_banner1)

    inner class BannerHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        with(holder.binding) {
            Glide.with(bannerImage)
                .load(bannerList[position])
                .into(bannerImage)
        }
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }
}