package com.example.test_android.presentation.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_android.data.RepositoryImpl
import com.example.test_android.data.room.ProductDatabase
import com.example.test_android.domain.ui_model.Product
import com.example.test_android.presentation.di.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    var products = ArrayList<Product>()
    val filteredProductList = MutableLiveData<List<Product>>()
    var currentCategory = 0
    val categoryList = ArrayList<String>()

    private var db: ProductDatabase = ProductDatabase.getInstance(App.getContext()!!)
    private var productDao = db.contactDao()

    var connection = MutableLiveData<Boolean>()

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                products = repository.getListOfProducts()
                products.forEach { product ->
                    if (!categoryList.contains(product.category))
                        product.category?.let { it1 -> categoryList.add(it1) }
                }
                withContext(Dispatchers.Main) {
                    currentCategory = 0
                    filterProductList()
                }
                productDao?.clearTable()
                productDao?.insertAll(products)
            } catch (ex: Exception) {
                println("Err")
            }
        }
    }

    fun restoreData(){
        CoroutineScope(Dispatchers.IO).launch() {
            products = productDao?.getAll() as ArrayList<Product>
            products.forEach { product ->
                if (!categoryList.contains(product.category))
                    product.category?.let { it1 -> categoryList.add(it1) }
            }
            withContext(Dispatchers.Main) {
                currentCategory = 0
                filterProductList()
            }
        }
    }

    fun filterProductList() {
        filteredProductList.value = products.filter { it.category == categoryList[currentCategory] }
    }

    fun changeCurrentCategory(newCategory: Int) {
        currentCategory = newCategory
    }
}