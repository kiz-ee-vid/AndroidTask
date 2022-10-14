package com.example.test_android.data.room

import androidx.room.*
import com.example.test_android.domain.ui_model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): MutableList<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Product?)
}