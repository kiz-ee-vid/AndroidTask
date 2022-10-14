package com.example.test_android.domain.ui_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductList(
    var productList: ArrayList<Product> = ArrayList()
):Parcelable

@Parcelize
data class Product(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var category: String? = null,
    var image: String? = null
):Parcelable