package com.example.test_android.data

import com.example.test_android.domain.ui_model.Product

data class ApiItem(
    var id: Int?,
    var title: String?,
    var price: Double?,
    var description: String?,
    var category: String?,
    var image: String?
) {
    fun toProduct(): Product {
        return Product(id, title, description, category, price?.toInt(), image)
    }
}