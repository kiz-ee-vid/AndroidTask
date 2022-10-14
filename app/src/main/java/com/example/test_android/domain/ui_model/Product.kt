package com.example.test_android.domain.ui_model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductList(
    var productList: ArrayList<Product> = ArrayList()
):Parcelable

@Entity
@Parcelize
data class Product(
    @PrimaryKey
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var category: String? = null,
    var price: Int? = null,
    var image: String? = null
):Parcelable