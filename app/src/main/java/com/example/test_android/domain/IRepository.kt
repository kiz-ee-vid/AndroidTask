package com.example.test_android.domain

import com.example.test_android.domain.ui_model.Product

interface IRepository {
    suspend fun getListOfProducts(): ArrayList<Product>
}