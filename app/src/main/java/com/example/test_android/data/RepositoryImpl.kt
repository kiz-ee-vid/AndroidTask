package com.example.test_android.data

import com.example.test_android.domain.IRepository
import com.example.test_android.domain.ui_model.Product
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : IRepository {

    override suspend fun getListOfProducts(): ArrayList<Product> {
        val productList = ArrayList<Product>()
        apiService.getProducts().body()?.forEach { productList.add(it.toProduct()) }
        return productList
    }
}