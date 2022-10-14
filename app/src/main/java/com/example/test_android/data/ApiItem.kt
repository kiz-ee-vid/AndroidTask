package com.example.test_android.data

import android.os.Parcelable
import com.example.test_android.domain.ui_model.Product
import com.example.test_android.domain.ui_model.ProductList
import kotlinx.parcelize.Parcelize

data class ApiItem(
    var id: Int?,
    var title: String?,
    var price: Double?,
    var description: String?,
    var category: String?,
    var image: String?
) {
    fun toProduct(): Product {
        return Product(id, title, description, category, image)
    }
}

data class ApiItemList(
    var data: ArrayList<ApiItem>
) {
    fun toProductList(): ProductList {
        val productList: ArrayList<Product> = ArrayList()
        data.forEach {
            productList.add(it.toProduct())
        }
        return ProductList(productList)
    }
}