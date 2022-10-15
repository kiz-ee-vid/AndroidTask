package com.example.test_android.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_android.domain.ui_model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): MutableList<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(orders: List<Product>)

    @Query("DELETE FROM product")
    fun clearTable()
}