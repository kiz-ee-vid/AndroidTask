package com.example.test_android.data.room

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import com.example.test_android.domain.ui_model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun contactDao(): ProductDao?

    companion object{
        private var sInstance: ProductDatabase? = null

        fun getInstance(context: Context): ProductDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, ProductDatabase::class.java, "test")
                    .build()
            }
            return sInstance!!
        }
    }
}