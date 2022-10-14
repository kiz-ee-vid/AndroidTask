package com.example.test_android.domain

import com.example.test_android.data.ApiItem
import com.example.test_android.domain.ui_model.Product
import com.example.test_android.domain.ui_model.ProductList

interface IRepository {
    suspend fun getListOfProducts(): ArrayList<Product>
}