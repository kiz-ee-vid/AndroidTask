package com.example.test_android.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_android.data.RepositoryImpl
import com.example.test_android.domain.ui_model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    private var products = ArrayList<Product>()
    val filteredProductList = MutableLiveData<List<Product>>()
    var currentCategory = 0
    val categoryList = ArrayList<String>()

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
            }
            catch (ex: Exception) {
                println("Err")
            }
        }
    }

    fun filterProductList(){
        filteredProductList.value = products.filter { it.category == categoryList[currentCategory] }
//        val list = ArrayList<Product>()
//        products.forEach {
//            if(it.category == categoryList[currentCategory])
//                list.add(it)
//        }
//        filteredProductList.value  = list
    }

    fun changeCurrentCategory(newCategory: Int){
        currentCategory = newCategory
    }
}