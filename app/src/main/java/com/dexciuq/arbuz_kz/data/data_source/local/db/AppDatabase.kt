package com.dexciuq.arbuz_kz.data.data_source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dexciuq.arbuz_kz.data.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "arbuz_kz"
    }
}